package com.vodafone.visassessmentsvc.resources.device.boundary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vodafone.visassessmentsvc.repository.iot.devices.boundary.DevicesRepository;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import com.vodafone.visassessmentsvc.repository.sim.boundary.SimsRepository;
import com.vodafone.visassessmentsvc.resources.device.entity.DeviceModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeviceResourceIT {
    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    DevicesRepository devicesRepository;

    @Autowired
    SimsRepository simsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        devicesRepository.deleteAll();
        simsRepository.deleteAll();
    }

    @Test
    public void testGetAllDevicesWaitingActivation() throws Exception {

        DeviceEntity deviceEntity = new DeviceEntity(1L, null, Status.NOT_READY, 15);
        devicesRepository.save(deviceEntity);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/iot-device/waiting-activation")
                .param("page", String.valueOf(0))
                .param("size", String.valueOf(2))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());

        final ObjectNode node = new ObjectMapper().readValue(response.getContentAsString(), ObjectNode.class);

        DeviceModel[] deviceModels = objectMapper.readValue(node.get("content").toString(), DeviceModel[].class);
        List<DeviceModel> deviceModelList = List.of(deviceModels);

        Assertions.assertEquals(1, deviceModelList.size());
        Assertions.assertEquals(deviceEntity.getStatus().name(), deviceModelList.get(0).getStatus());
        Assertions.assertEquals(deviceEntity.getTemperature(), deviceModelList.get(0).getTemperature());
        Assertions.assertEquals(deviceEntity.getId(), deviceModelList.get(0).getId());
        devicesRepository.deleteAll();
    }
}
