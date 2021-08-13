INSERT INTO `devices` (`id`,`status`,`temperature`) VALUES (1,'NOT_READY',12);
INSERT INTO `devices` (`id`,`status`,`temperature`) VALUES (2,'NOT_READY',12);
INSERT INTO `devices` (`id`,`status`,`temperature`) VALUES (5,'NOT_READY',12);
INSERT INTO `devices` (`id`,`status`,`temperature`) VALUES (3,'NOT_READY',12);

INSERT INTO `devices` (`id`,`status`,`temperature`,`sim_id`) VALUES (12,'READY',12,12);
INSERT INTO `devices` (`id`,`status`,`temperature`,`sim_id`) VALUES (15,'READY',12,15);
INSERT INTO `devices` (`id`,`status`,`temperature`,`sim_id`) VALUES (13,'READY',12,13);

INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (1,'EGYPT','012','WAITING_FOR_ACTIVATION');
INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (2,'CAIRO','013','WAITING_FOR_ACTIVATION');
INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (5,'EGYPT','012','WAITING_FOR_ACTIVATION');
INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (3,'EGYPT','013','WAITING_FOR_ACTIVATION');

INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (12,'CAIRO','013','ACTIVE');
INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (15,'EGYPT','012','ACTIVE');
INSERT INTO `sims` (`sim_id`,`country`,`operator_code`,`status`) VALUES (13,'EGYPT','013','ACTIVE');
