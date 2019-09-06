--
-- Table structure for table `Parent_Task`
--

CREATE TABLE `Parent_Task` (
  `Parent_ID` int(11) NOT NULL,
  `Parent_Task` varchar(500) NOT NULL,
  CONSTRAINT parent_pk PRIMARY KEY (`Parent_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Parent Task Table';

--
-- Initial Values for table `parent_task`
--

INSERT INTO `parent_task` (`parent_id`, `parent_task`) VALUES
(1, 'Parent Task 1'),
(2, 'Parent Task 2'),
(3, 'Parent Task 3');


CREATE TABLE `Task` (
  `Task_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Parent_Id` int(11) NOT NULL,
  `Task` varchar(500) NOT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  `Priority` int(11) NOT NULL,
  `Status` varchar(15) NOT NULL,
   CONSTRAINT task_pk PRIMARY KEY (`Task_Id`)
   FOREIGN KEY fk_task(`Parent_ID`) REFERENCES `Parent_Task` (`Parent_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Task Table';

