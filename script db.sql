CREATE DATABASE `hospitalqcm` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

CREATE TABLE `contact` (
  `id_contact` int(11) NOT NULL AUTO_INCREMENT,
  `date_contact` date NOT NULL,
  `id_reponse_final` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `texte_contact` varchar(250) NOT NULL,
  `id_medecin` int(11) NOT NULL,
  PRIMARY KEY (`id_contact`),
  KEY `i_id_user` (`id_user`),
  KEY `i_id_medecin` (`id_medecin`),
  KEY `i_reponse_final` (`id_reponse_final`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_connect` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id_medecin`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`id_reponse_final`) REFERENCES `reponse_final` (`id_reponse_final`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.medecin definition

CREATE TABLE `medecin` (
  `id_medecin` int(11) NOT NULL AUTO_INCREMENT,
  `id_theme` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `numero_inami` int(11) NOT NULL,
  PRIMARY KEY (`id_medecin`),
  UNIQUE KEY `id_user` (`id_user`),
  CONSTRAINT `medecin_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_connect` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.questions definition

CREATE TABLE `questions` (
  `id_questions` int(11) NOT NULL AUTO_INCREMENT,
  `id_theme` int(11) NOT NULL,
  `texte_questions` varchar(250) NOT NULL,
  PRIMARY KEY (`id_questions`),
  KEY `i_theme` (`id_theme`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`id_theme`) REFERENCES `theme` (`id_theme`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.questions_affinée definition

CREATE TABLE `questions_affinée` (
  `id_question_affinée` int(11) NOT NULL AUTO_INCREMENT,
  `id_reponse_final` int(11) NOT NULL,
  `texte_question_affinée` varchar(250) NOT NULL,
  PRIMARY KEY (`id_question_affinée`),
  KEY `i_reponse_final` (`id_reponse_final`),
  CONSTRAINT `questions_affinée_ibfk_1` FOREIGN KEY (`id_reponse_final`) REFERENCES `reponse_final` (`id_reponse_final`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.reponse_final definition

CREATE TABLE `reponse_final` (
  `id_reponse_final` int(11) NOT NULL AUTO_INCREMENT,
  `id_medecin` int(11) DEFAULT NULL,
  `texte_question` text NOT NULL,
  `id_resultat_valeur` int(11) NOT NULL,
  PRIMARY KEY (`id_reponse_final`),
  KEY `i_medecin` (`id_medecin`),
  KEY `id_i_resultat_valeur` (`id_resultat_valeur`),
  CONSTRAINT `reponse_final_ibfk_1` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id_medecin`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.reponse_questions definition

CREATE TABLE `reponse_questions` (
  `id_reponses_questions` int(11) NOT NULL AUTO_INCREMENT,
  `id_questions` int(11) NOT NULL,
  `texte_question` varchar(250) NOT NULL,
  `valeur` int(11) NOT NULL,
  PRIMARY KEY (`id_reponses_questions`),
  KEY `i_id_question` (`id_questions`),
  CONSTRAINT `reponse_questions_ibfk_1` FOREIGN KEY (`id_questions`) REFERENCES `questions` (`id_questions`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.reponses_user definition

CREATE TABLE `reponses_user` (
  `id_reponse_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_reponse_final` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `valeur_final` int(11) NOT NULL,
  PRIMARY KEY (`id_reponse_user`),
  KEY `i_user` (`id_user`) USING BTREE,
  KEY `i_reponse_final` (`id_reponse_final`) USING BTREE,
  CONSTRAINT `reponses_user_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_connect` (`id_user`),
  CONSTRAINT `reponses_user_ibfk_2` FOREIGN KEY (`id_reponse_final`) REFERENCES `reponse_final` (`id_reponse_final`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.resultat_valeur_qcm definition

CREATE TABLE `resultat_valeur_qcm` (
  `id_resultat_valeur` int(11) NOT NULL AUTO_INCREMENT,
  `id_theme` int(11) NOT NULL,
  `valeur_min` int(11) NOT NULL,
  `valeur_max` int(11) NOT NULL,
  PRIMARY KEY (`id_resultat_valeur`),
  KEY `i_theme` (`id_theme`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.roles definition

CREATE TABLE `roles` (
  `id_roles` int(11) NOT NULL AUTO_INCREMENT,
  `nom_roles` varchar(25) NOT NULL,
  PRIMARY KEY (`id_roles`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;


-- hospitalqcm.theme definition

CREATE TABLE `theme` (
  `id_theme` int(11) NOT NULL AUTO_INCREMENT,
  `nom_theme` varchar(25) NOT NULL,
  PRIMARY KEY (`id_theme`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- hospitalqcm.user_connect definition

CREATE TABLE `user_connect` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nom_user` varchar(25) NOT NULL,
  `phone_user` int(11) NOT NULL,
  `prenom_user` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mail_user` varchar(50) NOT NULL,
  `id_roles` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `id-role` (`id_roles`),
  CONSTRAINT `user_connect_ibfk_1` FOREIGN KEY (`id_roles`) REFERENCES `roles` (`id_roles`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;


