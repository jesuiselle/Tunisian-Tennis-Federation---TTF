-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 17 Mai 2016 à 07:24
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `tft_6`
--

-- --------------------------------------------------------

--
-- Structure de la table `actualite`
--

CREATE TABLE IF NOT EXISTS `actualite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `contenu` text COLLATE utf8_unicode_ci NOT NULL,
  `date_publication` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Contenu de la table `actualite`
--

INSERT INTO `actualite` (`id`, `titre`, `contenu`, `date_publication`, `image`) VALUES
(8, 'Il y a deux mois, je ne pensais pas être 31e mondi', 'Nous avons retrouvé Lucas Pouille dans les allées de Roland-Garros, lundi. Le Nordiste vient de vivre une période phare et se retrouve 31e au classement ATP, ce qui lui assure un statut de tête de série à Paris.\r\n\r\nC''est le rendez-vous du printemps. Le rendez-vous des amoureux de la terre battue. Et d''un certain Rafael Nadal. Comme tous les ans, Djokovic, Federer et les autres s''attaquent au royaume de l''Espagnol. Pour s''y casser une nouvelle fois les dents ? \r\n\r\nRésumé des matches, meilleurs coups, interviews et insolites : vous ne manquerez rien de Roland-Garros grâce aux vidéos d''Eurosport.fr.\r\n\r\nRetrouvez Eurosport et Eurosport 2 au sein des bouquets Canalsat.Eurosport disponible uniquement sur CANALSAT', '16-05-2016', 'a49d9e882d6e9a9e271d534a19da5348.png'),
(9, 'Novak Djokovic en finale à Rome, dimanche', 'Un partout balle au centre entre Murray et Djokovic. Une semaine après sa défaite en finale à Madrid, le Britannique a rendu la pareille à Novak Djokovic, dimanche, en finale du Masters 1000 de Rome en s''imposant en deux manches (6-3, 6-3). Au terme d''un combat joué en partie sous la pluie, l''Ecossais a été le plus fort mentalement face à un N.1 mondial qui est apparu très nerveux tout au long de la rencontre. Plus fort, il s''est adjugé son premier titre au Foro Italico, le jour de ses 29 ans et son premier sacre en 2016. A une semaine de Roland-Garros voilà le 3e joueur mondial, qui a remporté son 12e Masters 1000 en carrière, comme un candidat sérieux à Paris.', '15-05-2016', '6907f8e4ab696f1faa00a56ede931a31.png'),
(10, 'Murray reprend sa place de dauphin, Ferrer ejecté', 'CLASSEMENT ATP - Andy Murray a retrouvé sa place de dauphin de Novak Djokovic à la faveur de sa victoire à Rome. David Ferrer est lui sorti du top 10 alors que Lucas Pouille poursuit son ascension.\r\nL''interim n''aura duré qu''une semaine : l''Ecossais Andy Murray, triomphateur à Rome, a récupéré sa place de N.2 mondial dans le nouveau classement ATP publié lundi. Il figure derrière le Serbe Novak Djokovic, toujours largement en tête malgré sa défaite en finale au Foro Italico, et devant le Suisse', '14-05-2016', 'ccd56e9d0d01290a784b8f65b4e5f376.png');

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE IF NOT EXISTS `administrateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `administrateur`
--

INSERT INTO `administrateur` (`id`, `nom`, `prenom`) VALUES
(3, 'teamrise', 'pidev');

-- --------------------------------------------------------

--
-- Structure de la table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_50D0C6061E27F6BF` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `arbitre`
--

CREATE TABLE IF NOT EXISTS `arbitre` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `categorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_naissance` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `arbitre`
--

INSERT INTO `arbitre` (`id`, `nom`, `prenom`, `categorie`, `date_naissance`, `image`) VALUES
(2, 'Eva', 'Asderaki', 'Professionnel', '30-12-1981', '99e25fbbeb4c78720f8e586cf9e43f9c.jpeg'),
(7, 'Bob', 'Strachan', 'Professionnel', '24-06-1987', '3f454193add2ca86177d363e9a851fa6.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

CREATE TABLE IF NOT EXISTS `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_fondation` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `logo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `club`
--

INSERT INTO `club` (`id`, `nom`, `adresse`, `date_fondation`, `logo`) VALUES
(1, 'Fédération Tunisienne de Tennis', 'Cité Nationale Sportive El Menzah  B.P 350 Tunis 1004', '10-10-1948', '5f37fdccc56a47f6cb2f47019f7824c1.png'),
(2, 'Roland Garros', 'à Paris', '06-10-1928', '3c65438b5664ff3c77c67ee177c169f7.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int(11) DEFAULT NULL,
  `id_actualite` int(11) DEFAULT NULL,
  `contenu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_utilisateur` (`id_utilisateur`),
  KEY `id_actualite` (`id_actualite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `compte_rendu_match`
--

CREATE TABLE IF NOT EXISTS `compte_rendu_match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_match` int(11) DEFAULT NULL,
  `id_arbitre` int(11) DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_match` (`id_match`),
  KEY `id_arbitre` (`id_arbitre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `compte_rendu_test`
--

CREATE TABLE IF NOT EXISTS `compte_rendu_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_medecin` int(11) DEFAULT NULL,
  `id_responsable` int(11) DEFAULT NULL,
  `id_joueur` int(11) DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `resultat` tinyint(1) NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_medecin` (`id_medecin`),
  KEY `id_responsable` (`id_responsable`),
  KEY `id_joueur` (`id_joueur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `disponibilite`
--

CREATE TABLE IF NOT EXISTS `disponibilite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_arbitre` int(11) DEFAULT NULL,
  `date` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `time` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_arbitre` (`id_arbitre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `don`
--

CREATE TABLE IF NOT EXISTS `don` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_club` int(11) DEFAULT NULL,
  `date_don` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_club` (`id_club`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `don`
--

INSERT INTO `don` (`id`, `id_club`, `date_don`, `description`) VALUES
(1, 1, '16-05-2016', '1000 balls');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `date_debut` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `gagnant` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `description`, `date_debut`, `date_fin`, `prix`, `gagnant`, `image`) VALUES
(1, 'US OPEN', 'The United States Open Tennis Championships is a hardcourt tennis tournament which is the modern version of one of the oldest tennis championships in the world, the U.S. National Championship, for which men''s singles was first contested in 1881. Since 1987, the US Open has been chronologically the fourth and final tennis major comprising the Grand Slam each year; the other three are the Australian Open, French Open and Wimbledon.', '25-03-2016', '27-04-2016', 550.14, NULL, 'fc67f89c86deb5d1727a761febc1a56f.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `fan`
--

CREATE TABLE IF NOT EXISTS `fan` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE IF NOT EXISTS `formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_debut` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date_fin` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`id`, `date_debut`, `date_fin`, `description`) VALUES
(1, '02-05-2016', '28-05-2016', 'Devenir Coach de Tennis : si vous avez toujours eu envie de devenir entraîneur de tennis, inscrivez vous à notre pré-formation du DEJEPS pour devenir coach.');

-- --------------------------------------------------------

--
-- Structure de la table `invitation`
--

CREATE TABLE IF NOT EXISTS `invitation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_joueur` int(11) DEFAULT NULL,
  `id_medecin` int(11) DEFAULT NULL,
  `date_invitation` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_joueur` (`id_joueur`),
  KEY `id_medecin` (`id_medecin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE IF NOT EXISTS `joueur` (
  `id` int(11) NOT NULL,
  `id_club` int(11) DEFAULT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `date_naissance` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `taille` double NOT NULL,
  `poids` double NOT NULL,
  `score` double NOT NULL,
  `categorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `groupe_age` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nationalite` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `logo_pays` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_club` (`id_club`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `joueur`
--

INSERT INTO `joueur` (`id`, `id_club`, `nom`, `prenom`, `date_naissance`, `taille`, `poids`, `score`, `categorie`, `groupe_age`, `image`, `nationalite`, `logo_pays`) VALUES
(4, 1, 'Jaziri', 'Malek', '22-07-1981', 1.81, 74, 734, 'Professionnel', 'Senior', '61b431e0f9b751c116ad0cc3cec80259.jpeg', 'Tunisie', 'ac595c531246266002c0315386459d71.jpeg'),
(8, 1, 'Triki', 'Ahmed', '21-10-1983', 1.79, 71, 135, 'Professionnel', 'Senior', '4db5f56d9a039c0e471a8bb85b56ac7d.jpeg', 'Tunisie', '033a6268ee8a4e4670d3dcf04273936a.jpeg'),
(9, 2, 'Nadal', 'Rafael', '21-05-1980', 1.86, 76, 7652, 'Professionnel', 'Senior', '340cb7fd346aa2e774c8ed3bc92db30a.png', 'Espagne', '290e7303919a84e92393987431dc3150.jpeg'),
(10, 2, 'Federer', 'Roger', '20-11-1981', 1.83, 74, 8124, 'Professionnel', 'Senior', '7ea95462e6f6d91e1dae2133ba989b91.jpeg', 'Suisse', '2419e6b4717a27c590f766a807acf9ac.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `kuma_voting_downvote`
--

CREATE TABLE IF NOT EXISTS `kuma_voting_downvote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `reference` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `kuma_voting_facebooklike`
--

CREATE TABLE IF NOT EXISTS `kuma_voting_facebooklike` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `reference` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `kuma_voting_facebooksend`
--

CREATE TABLE IF NOT EXISTS `kuma_voting_facebooksend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `reference` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `kuma_voting_linkedinshare`
--

CREATE TABLE IF NOT EXISTS `kuma_voting_linkedinshare` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `reference` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `kuma_voting_upvote`
--

CREATE TABLE IF NOT EXISTS `kuma_voting_upvote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `reference` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meta` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE IF NOT EXISTS `medecin` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `medecin`
--

INSERT INTO `medecin` (`id`, `nom`, `prenom`, `image`) VALUES
(5, 'Eva', 'Carneiro', '318d48f71283e53dec80368cd31c180c.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

CREATE TABLE IF NOT EXISTS `media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `titre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `source` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Contenu de la table `media`
--

INSERT INTO `media` (`id`, `genre`, `titre`, `source`) VALUES
(1, 'Video', 'Pouille : "Il y a deux mois, je ne pensais pas être 31e mondial"', 'https://www.youtube.com/watch?v=f88QwjHIv9w'),
(2, 'Video', 'Guez fait sensation pour le début des qualifs', 'https://www.youtube.com/watch?v=cocLQ5OotNU'),
(3, 'Video', 'Tennis Greatest Points Ever', 'https://www.youtube.com/watch?v=mz6Kti6tZso'),
(4, 'Video', 'Roger Federer vs Milos Raonic - FINAL Brisbane', 'https://www.youtube.com/watch?v=GanokmITGDQ'),
(5, 'Video', 'Grigor Dimitrov vs Roger Federer', 'https://www.youtube.com/watch?v=0wJUcFxUrsg'),
(6, 'Video', 'Serena Williams vs Angelique Kerber', 'https://www.youtube.com/watch?v=0FTsFuZrxe4'),
(7, 'Video', 'Tennis - Sportsmanship moments', 'https://www.youtube.com/watch?v=ZqU8B_M_rT0'),
(8, 'Image', 'La "nouvelle'''' Tunisie refuse de jouer au tennis avec un Israélien', '/../../resources/images/Jaziri.jpeg'),
(9, 'Image', 'Tennis : Ons Jabeur en Finale du tournoi Nana Trophy', '/../../resources/images/Ons.jpg'),
(10, 'Image', 'Tennis: Nadal se rapproche du record de Vilas', '/../../resources/images/l.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `participation_formation`
--

CREATE TABLE IF NOT EXISTS `participation_formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_arbitre` int(11) DEFAULT NULL,
  `id_formation` int(11) DEFAULT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_arbitre` (`id_arbitre`),
  KEY `id_formation` (`id_formation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

CREATE TABLE IF NOT EXISTS `partie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stade` int(11) DEFAULT NULL,
  `id_evenement` int(11) DEFAULT NULL,
  `id_arbitre` int(11) DEFAULT NULL,
  `id_joueur_trois` int(11) DEFAULT NULL,
  `id_joueur_quatre` int(11) DEFAULT NULL,
  `id_joueur_un` int(11) DEFAULT NULL,
  `id_joueur_deux` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `genre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `score` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `set_un` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `set_deux` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `set_trois` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `set_quatre` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `set_cinq` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_match` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_stade` (`id_stade`),
  KEY `id_evenement` (`id_evenement`),
  KEY `id_arbitre` (`id_arbitre`),
  KEY `id_joueur1` (`id_joueur_un`,`id_joueur_deux`,`id_joueur_trois`,`id_joueur_quatre`),
  KEY `id_joueur1_2` (`id_joueur_un`),
  KEY `id_joueur2` (`id_joueur_deux`),
  KEY `id_joueur3` (`id_joueur_trois`),
  KEY `id_joueur4` (`id_joueur_quatre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Contenu de la table `partie`
--

INSERT INTO `partie` (`id`, `id_stade`, `id_evenement`, `id_arbitre`, `id_joueur_trois`, `id_joueur_quatre`, `id_joueur_un`, `id_joueur_deux`, `description`, `genre`, `type`, `score`, `set_un`, `set_deux`, `set_trois`, `set_quatre`, `set_cinq`, `date_match`, `lien`) VALUES
(1, 1, 1, 2, NULL, NULL, 10, 9, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i', 'Single', 'Men', '3-1', '6-4', '6-3', '5-7', '6-4', NULL, '28-03-2016', 'https://www.youtube.com/watch?v=q7AiwWwiF_k'),
(2, 1, 1, 2, NULL, NULL, 10, 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i', 'Single', 'Men', '3-0', '6-2', '6-1', '6-1', NULL, NULL, '01-04-2016', 'https://www.youtube.com/watch?v=0QjtcQ7-z74'),
(3, 1, 1, 2, NULL, NULL, 4, 8, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor i', 'Single', 'Men', '*-*', '*-*', '*-*', NULL, NULL, NULL, '23-04-2016', 'https://www.youtube.com/watch?v=autidG52oPI'),
(4, 1, 1, 2, NULL, NULL, 9, 10, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur pharetra vitae odio sit amet vulputate. Curabitur in mi vel ante tempus consectetur at eu nunc. Suspendisse sed ex ac velit elementum interdum quis dictum leo. Sed eu erat nulla. Curabitur', 'Single', 'Men', '3-1', '4-6', '6-4', '6-3', '7-5', NULL, '17-04-2016', 'https://www.youtube.com/watch?v=LzpoNmNZIXk'),
(5, 1, 1, 2, NULL, NULL, 10, 8, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin pellentesque augue vel velit volutpat semper. Morbi sit amet lectus eget mauris laoreet pharetra.', 'Single', 'Men', '3-0', '6-0', '6-1', '6-0', NULL, NULL, '07-04-2016', 'https://www.youtube.com/watch?v=RfG5OC9wMXA');

-- --------------------------------------------------------

--
-- Structure de la table `pronostic`
--

CREATE TABLE IF NOT EXISTS `pronostic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_gagnant1` int(11) DEFAULT NULL,
  `id_match5` int(11) DEFAULT NULL,
  `id_gagnant5` int(11) DEFAULT NULL,
  `id_match6` int(11) DEFAULT NULL,
  `id_gagnant6` int(11) DEFAULT NULL,
  `id_match7` int(11) DEFAULT NULL,
  `id_gagnant7` int(11) DEFAULT NULL,
  `id_match8` int(11) DEFAULT NULL,
  `id_gagnant8` int(11) DEFAULT NULL,
  `id_match9` int(11) DEFAULT NULL,
  `id_gagnant9` int(11) DEFAULT NULL,
  `id_match1` int(11) DEFAULT NULL,
  `id_match10` int(11) DEFAULT NULL,
  `id_gagnant10` int(11) DEFAULT NULL,
  `id_match11` int(11) DEFAULT NULL,
  `id_gagnant11` int(11) DEFAULT NULL,
  `id_match12` int(11) DEFAULT NULL,
  `id_gagnant12` int(11) DEFAULT NULL,
  `id_fan` int(11) DEFAULT NULL,
  `id_match2` int(11) DEFAULT NULL,
  `id_gagnant2` int(11) DEFAULT NULL,
  `id_match3` int(11) DEFAULT NULL,
  `id_gagnant3` int(11) DEFAULT NULL,
  `id_match4` int(11) DEFAULT NULL,
  `id_gagnant4` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_joueur` (`id_gagnant1`),
  KEY `id_match` (`id_match1`),
  KEY `id_fan` (`id_fan`),
  KEY `id_match2` (`id_match2`),
  KEY `id_gagnant2` (`id_gagnant2`),
  KEY `id_match3` (`id_match3`),
  KEY `id_gagnant3` (`id_gagnant3`),
  KEY `id_match4` (`id_match4`),
  KEY `id_gagnant4` (`id_gagnant4`),
  KEY `id_match5` (`id_match5`),
  KEY `id_gagnant5` (`id_gagnant5`),
  KEY `id_match6` (`id_match6`),
  KEY `id_gagnant6` (`id_gagnant6`),
  KEY `id_match7` (`id_match7`),
  KEY `id_gagnant7` (`id_gagnant7`),
  KEY `id_match8` (`id_match8`),
  KEY `id_gagnant8` (`id_gagnant8`),
  KEY `id_match9` (`id_match9`),
  KEY `id_gagnant9` (`id_gagnant9`),
  KEY `id_match10` (`id_match10`),
  KEY `id_gagnant10` (`id_gagnant10`),
  KEY `id_match11` (`id_match11`),
  KEY `id_gagnant11` (`id_gagnant11`),
  KEY `id_match12` (`id_match12`),
  KEY `id_gagnant12` (`id_gagnant12`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

CREATE TABLE IF NOT EXISTS `responsable` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `responsable`
--

INSERT INTO `responsable` (`id`, `nom`, `prenom`) VALUES
(6, 'Jean', 'Pierre');

-- --------------------------------------------------------

--
-- Structure de la table `stade`
--

CREATE TABLE IF NOT EXISTS `stade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `lieu` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `stade`
--

INSERT INTO `stade` (`id`, `nom`, `lieu`, `description`, `image`) VALUES
(1, 'Rod Laver', 'USA', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'f9f38729543397913d56f8edc2eee65e.jpeg'),
(2, 'Stade olympique d''El Menzah', 'Tunisie', 'Le stade olympique d''El Menzah est situé au nord de Tunis, plus précisément dans le quartier d''El Menzah.', 'adaa2e2f90c8e1f8f8ab3567c2d89e8a.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `statistic`
--

CREATE TABLE IF NOT EXISTS `statistic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_match` int(11) DEFAULT NULL,
  `id_joueur` int(11) DEFAULT NULL,
  `aces` int(11) NOT NULL,
  `service_winners` int(11) NOT NULL,
  `double_faults` int(11) NOT NULL,
  `total_points` int(11) NOT NULL,
  `total_points_won` int(11) NOT NULL,
  `service_game` int(11) NOT NULL,
  `average_serve_speed` double NOT NULL,
  `fastest_serve_speed` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_match` (`id_match`),
  KEY `id_joueur` (`id_joueur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `statistic`
--

INSERT INTO `statistic` (`id`, `id_match`, `id_joueur`, `aces`, `service_winners`, `double_faults`, `total_points`, `total_points_won`, `service_game`, `average_serve_speed`, `fastest_serve_speed`) VALUES
(1, 1, 4, 2, 6, 3, 5, 4, 6, 5, 10);

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_match` int(11) DEFAULT NULL,
  `id_fan` int(11) DEFAULT NULL,
  `etat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_match` (`id_match`),
  KEY `id_fan` (`id_fan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `credentials_expired` tinyint(1) NOT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `facebook_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_1D1C63B392FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_1D1C63B3A0D96FBF` (`email_canonical`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `type`, `facebook_id`) VALUES
(2, 'Eva', 'eva', 'Eva@tft.tn', 'eva@tft.tn', 1, 'aetvap2h2e8kwgwgg8cwskcsc0g0c8g', '$2y$12$aetvap2h2e8kwgwgg8cwse7yUXD0Hr0We7IPyLC5OWDxMIzt1FIS2', '2016-04-16 23:45:58', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:12:"ROLE_ARBITRE";}', 0, NULL, 'arbitre', NULL),
(3, 'hatem4231', 'hatem4231', 'testpidev@gmail.com', 'testpidev@gmail.com', 1, 'eygqgf98mp4ocksswg8kg48kgkkc8k', '$2a$12$Ep2vI8kw6amW0zIeIpjWgeNEjhkLYMCpMgcVDY7rhl3.yQ7KDrcXm', '2016-05-17 01:34:46', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:10:"ROLE_ADMIN";}', 0, NULL, 'administrateur', NULL),
(4, 'jmalek', 'jmalek', 'jaziri.malek@test.tn', 'jaziri.malek@test.tn', 1, 'jqkzig66qhw0c4csckk4woocowks8ws', '$2y$12$jqkzig66qhw0c4csckk4weWxKT/qvnW38FapbqY/4lbqTme4pC.vK', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_JOUEUR";}', 0, NULL, 'joueur', NULL),
(5, 'Carneiro', 'carneiro', 'Carneiro@tft.tn', 'carneiro@tft.tn', 1, 'mlpnziyu9u8ckccwgggcosoog0cog0g', '$2y$12$mlpnziyu9u8ckccwgggcoedM6GxgWXHGrjiwNCxeXy/R0hMpBKcVC', '2016-05-16 18:04:39', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:12:"ROLE_MEDECIN";}', 0, NULL, 'medecin', NULL),
(6, 'Pierre', 'pierre', 'Pierre@tft.tn', 'pierre@tft.tn', 1, 'lealedj5y0gsgk448w8gc040kckc440', '$2y$12$lealedj5y0gsgk448w8gcuRB6BQORETx3L4mSKn8zmj6xRFBDkjiW', '2016-04-16 14:52:32', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:16:"ROLE_RESPONSABLE";}', 0, NULL, 'responsable', NULL),
(7, 'Bob', 'bob', 'Bob@test.tn1', 'bob@test.tn1', 1, '99johh8umyo0g4oc8440ss84kkocs8s', '$2y$12$99johh8umyo0g4oc8440seLhysSo.5Xo6thXNhfwy9qXCdcMo2gKm', '2016-04-16 13:32:12', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:12:"ROLE_ARBITRE";}', 0, NULL, 'arbitre', NULL),
(8, 'a.triki', 'a.triki', 'ahmed.triki@test.tn', 'ahmed.triki@test.tn', 1, 't7xk021sfn4o4k4wkcwoggcgc4g8k0o', '$2y$12$t7xk021sfn4o4k4wkcwogeC2LBhIXeVWhQrKGAi/Ir4kWxb7FbkB.', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_JOUEUR";}', 0, NULL, 'joueur', NULL),
(9, 'n.rafa', 'n.rafa', 'n.rafa@test.tn', 'n.rafa@test.tn', 1, 'o5r9ww34ic0cwgwowgwks80ocoogc4g', '$2y$12$o5r9ww34ic0cwgwowgwksuiuncE7B1eGo4/JgulzfNRDfBtnI0zg2', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_JOUEUR";}', 0, NULL, 'joueur', NULL),
(10, 'rogerf', 'rogerf', 'rogerf@test.tn', 'rogerf@test.tn', 1, 'kbs89v9v01csoc04o0gkgcoc848c0gg', '$2y$12$kbs89v9v01csoc04o0gkgO3lla87gHnb6ImrXogiyZZ7T53eYdo5e', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_JOUEUR";}', 0, NULL, 'joueur', NULL);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FK_32EB52E8BF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `FK_50D0C6061E27F6BF` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `arbitre`
--
ALTER TABLE `arbitre`
  ADD CONSTRAINT `FK_20B2E66EBF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_67F068BCD4A73DB0` FOREIGN KEY (`id_actualite`) REFERENCES `actualite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_67F068BC50EAE44` FOREIGN KEY (`id_utilisateur`) REFERENCES `fan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `compte_rendu_match`
--
ALTER TABLE `compte_rendu_match`
  ADD CONSTRAINT `FK_E9E79C7FFE2F4F1E` FOREIGN KEY (`id_arbitre`) REFERENCES `arbitre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E9E79C7F94DE8435` FOREIGN KEY (`id_match`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `compte_rendu_test`
--
ALTER TABLE `compte_rendu_test`
  ADD CONSTRAINT `FK_6DD99981DB461C28` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_6DD999814A40C0F0` FOREIGN KEY (`id_responsable`) REFERENCES `responsable` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_6DD99981C547FAB6` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `disponibilite`
--
ALTER TABLE `disponibilite`
  ADD CONSTRAINT `FK_2CBACE2FFE2F4F1E` FOREIGN KEY (`id_arbitre`) REFERENCES `arbitre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `don`
--
ALTER TABLE `don`
  ADD CONSTRAINT `FK_F8F081D933CE2470` FOREIGN KEY (`id_club`) REFERENCES `club` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fan`
--
ALTER TABLE `fan`
  ADD CONSTRAINT `FK_65F77839BF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `invitation`
--
ALTER TABLE `invitation`
  ADD CONSTRAINT `FK_F11D61A2C547FAB6` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_F11D61A2DB461C28` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id`);

--
-- Contraintes pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `FK_FD71A9C533CE2470` FOREIGN KEY (`id_club`) REFERENCES `club` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_FD71A9C5BF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD CONSTRAINT `FK_1BDA53C6BF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participation_formation`
--
ALTER TABLE `participation_formation`
  ADD CONSTRAINT `FK_2EC70FD4C0759D98` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_2EC70FD4FE2F4F1E` FOREIGN KEY (`id_arbitre`) REFERENCES `arbitre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `partie`
--
ALTER TABLE `partie`
  ADD CONSTRAINT `FK_59B1F3D770AD361` FOREIGN KEY (`id_joueur_deux`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_59B1F3D2F75DF08` FOREIGN KEY (`id_joueur_un`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_59B1F3D7D4819A` FOREIGN KEY (`id_stade`) REFERENCES `stade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_59B1F3D8B13D439` FOREIGN KEY (`id_evenement`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_59B1F3DCE8A6904` FOREIGN KEY (`id_joueur_trois`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_59B1F3DE7AE0FD8` FOREIGN KEY (`id_joueur_quatre`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_59B1F3DFE2F4F1E` FOREIGN KEY (`id_arbitre`) REFERENCES `arbitre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `pronostic`
--
ALTER TABLE `pronostic`
  ADD CONSTRAINT `FK_E64BDCDE5AD115CA` FOREIGN KEY (`id_gagnant4`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE1C49B0F3` FOREIGN KEY (`id_gagnant12`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE24606977` FOREIGN KEY (`id_gagnant9`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE2ABBE145` FOREIGN KEY (`id_gagnant1`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE2DD6255C` FOREIGN KEY (`id_gagnant5`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE3BF5943C` FOREIGN KEY (`id_match3`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE3C985025` FOREIGN KEY (`id_match7`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE4B9F60B3` FOREIGN KEY (`id_match6`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE4CF2A4AA` FOREIGN KEY (`id_match2`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE536759E1` FOREIGN KEY (`id_gagnant8`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE7B7559C` FOREIGN KEY (`id_match12`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE8540E149` FOREIGN KEY (`id_gagnant11`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDE9EBE0426` FOREIGN KEY (`id_match11`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEA591019F` FOREIGN KEY (`id_match4`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEAC274DB4` FOREIGN KEY (`id_match8`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEB3B2B0FF` FOREIGN KEY (`id_gagnant2`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEB4DF74E6` FOREIGN KEY (`id_gagnant6`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEC3D84470` FOREIGN KEY (`id_gagnant7`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEC4B58069` FOREIGN KEY (`id_gagnant3`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDED2963109` FOREIGN KEY (`id_match5`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDED5FBF510` FOREIGN KEY (`id_match1`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEDB207D22` FOREIGN KEY (`id_match9`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEE9B934B0` FOREIGN KEY (`id_match10`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEF247D1DF` FOREIGN KEY (`id_gagnant10`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_E64BDCDEFB539063` FOREIGN KEY (`id_fan`) REFERENCES `fan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD CONSTRAINT `FK_52520D07BF396750` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `statistic`
--
ALTER TABLE `statistic`
  ADD CONSTRAINT `FK_649B469CDB461C28` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_649B469C94DE8435` FOREIGN KEY (`id_match`) REFERENCES `partie` (`id`);

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK_97A0ADA394DE8435` FOREIGN KEY (`id_match`) REFERENCES `partie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_97A0ADA3FB539063` FOREIGN KEY (`id_fan`) REFERENCES `fan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
