-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 10 Lut 2024, 21:42
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `driver`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `car`
--

CREATE TABLE `car` (
  `idcar` int(11) NOT NULL,
  `manufacturer` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `car`
--

INSERT INTO `car` (`idcar`, `manufacturer`, `model`, `type`, `price`, `status`) VALUES
(1, 'Polak', 'cebula', 't2', 400, 1),
(2, 'Polak', 'burak', 't3', 600, 1),
(3, 'Honda', 'szybka', 'combi', 820, 1),
(4, 'Toyota', 'Yaris 2020', 'Osobowe', 700, 1),
(5, 'Opel', 'Mokka 2020', 'Crossover', 1100, 1),
(6, 'testowy', 'testowy', 'testowy', 400, 1),
(7, 'Check', 'Check', 'Check', 200, 3),
(8, 'Marakara', 'Marakara', 'Marakara', 1300, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `driver`
--

CREATE TABLE `driver` (
  `iddriver` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `speciality` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `idrole` int(11) NOT NULL,
  `rolename` varchar(50) NOT NULL,
  `cdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`idrole`, `rolename`, `cdate`) VALUES
(1, 'user', '2024-01-07 14:43:03'),
(2, 'admin', '2024-01-07 14:43:03'),
(3, 'driver', '2024-01-07 14:43:03'),
(4, 'worker', '2024-01-07 14:43:03'),
(5, 'blocked', '2024-01-07 14:43:03');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sessions`
--

CREATE TABLE `sessions` (
  `idsession` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `driver` int(11) DEFAULT NULL,
  `car` int(11) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `sessions`
--

INSERT INTO `sessions` (`idsession`, `user`, `driver`, `car`, `date`) VALUES
(1, 19, NULL, 4, '2024-02-03'),
(2, 15, NULL, 3, '2024-02-03'),
(3, 22, NULL, 8, '2024-02-03'),
(4, 1, NULL, 7, '2024-02-04'),
(5, 13, NULL, 5, '2024-02-06'),
(6, 13, NULL, 8, '2024-02-06'),
(7, 19, NULL, 5, '2024-02-07');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `cdate` date NOT NULL,
  `mdate` date NOT NULL,
  `cwho` int(11) NOT NULL,
  `mwho` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `role` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`iduser`, `name`, `surname`, `address`, `cdate`, `mdate`, `cwho`, `mwho`, `username`, `password`, `role`) VALUES
(1, 'Adam', 'Noga', '', '0000-00-00', '0000-00-00', 0, 0, 'Testowy', '1234', 1),
(2, 'Tomasz', 'Stray', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(3, 'Paweł', 'Kowalski', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(4, 'Anastazja', 'Nocna', '', '0000-00-00', '0000-00-00', 0, 0, 'Anocna', 'Anocna', 1),
(5, 'Jan', 'Masło', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(6, 'Natan', 'Owski', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 2),
(7, 'Ola', 'Nowa', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(8, 'Sosna', 'Modra', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(9, 'Natalia', 'Taka', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 3),
(10, 'Adam', 'Adam', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(11, 'Pani', 'Stara', '', '0000-00-00', '0000-00-00', 0, 0, 'pStara', 'pStara', 4),
(12, 'Pan', 'Nowy', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 5),
(13, 'Tomasz', 'Tester', '', '0000-00-00', '0000-00-00', 0, 0, 'TTester', '1234', 1),
(14, 'Jadwiga', 'Testowa', '', '0000-00-00', '0000-00-00', 0, 0, '', '', 1),
(15, 'Test', 'Test', '', '2024-01-12', '2024-01-12', 1, 1, 'Test', 'Test', 1),
(16, 'Adamas', 'Adamas', '', '2024-01-14', '2024-01-14', 1, 1, 'Adamas', 'Adamas', 1),
(17, 'Tola', 'Tola', '', '2024-01-14', '2024-01-14', 1, 1, 'Tola', 'Tola', 1),
(18, 'UWADA', 'UWADA', '', '2024-01-23', '2024-01-23', 1, 1, 'UWADA', 'UWADA', 4),
(19, 'user', 'user', '', '2024-02-02', '2024-02-02', 1, 1, 'user', 'user', 1),
(20, 'admin', 'admin', '', '2024-02-02', '2024-02-02', 1, 1, 'admin', 'admin', 2),
(21, 'worker', 'worker', '', '2024-02-02', '2024-02-02', 1, 1, 'worker', 'worker', 4),
(22, 'blocked', 'blocked', '', '2024-02-02', '2024-02-02', 1, 1, 'blocked', 'blocked', 5),
(23, 'konto', 'dla', '', '2024-02-09', '2024-02-09', 1, 1, 'testu', '1234', 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`idcar`),
  ADD KEY `status` (`status`);

--
-- Indeksy dla tabeli `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`iddriver`),
  ADD KEY `iduser` (`iduser`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idrole`);

--
-- Indeksy dla tabeli `sessions`
--
ALTER TABLE `sessions`
  ADD PRIMARY KEY (`idsession`),
  ADD KEY `user` (`user`),
  ADD KEY `driver` (`driver`),
  ADD KEY `car` (`car`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD KEY `role` (`role`) USING BTREE;

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `car`
--
ALTER TABLE `car`
  MODIFY `idcar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `driver`
--
ALTER TABLE `driver`
  MODIFY `iddriver` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `roles`
--
ALTER TABLE `roles`
  MODIFY `idrole` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `sessions`
--
ALTER TABLE `sessions`
  MODIFY `idsession` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `car_ibfk_1` FOREIGN KEY (`status`) REFERENCES `status` (`idstatus`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `driver`
--
ALTER TABLE `driver`
  ADD CONSTRAINT `driver_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`);

--
-- Ograniczenia dla tabeli `sessions`
--
ALTER TABLE `sessions`
  ADD CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`iduser`),
  ADD CONSTRAINT `sessions_ibfk_2` FOREIGN KEY (`driver`) REFERENCES `driver` (`iddriver`),
  ADD CONSTRAINT `sessions_ibfk_3` FOREIGN KEY (`car`) REFERENCES `car` (`idcar`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
