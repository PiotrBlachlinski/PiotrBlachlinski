<?php
// KONTROLER strony kalkulatora
require_once dirname(__FILE__).'/../config.php';

// W kontrolerze niczego nie wysyła się do klienta.
// Wysłaniem odpowiedzi zajmie się odpowiedni widok.
// Parametry do widoku przekazujemy przez zmienne.

// 1. pobranie parametrów

$N = $_REQUEST ['N'];
$n = $_REQUEST ['n'];
$r = $_REQUEST ['r'];


// 2. walidacja parametrów z przygotowaniem zmiennych dla widoku

// sprawdzenie, czy parametry zostały przekazane
if ( ! (isset($N) && isset($n) && isset($r))) {
	//sytuacja wystąpi kiedy np. kontroler zostanie wywołany bezpośrednio - nie z formularza
	$messages [] = 'Błędne wywołanie aplikacji. Brak jednego z parametrów.';
}

// sprawdzenie, czy potrzebne wartości zostały przekazane
if ( $N == "") {
	$messages [] = 'Nie podano kwoty kredytu';
}
if ( $n == "") {
	$messages [] = 'Nie podano ilości rat';
}	
	
if ( $r == "") {
	$messages [] = 'Nie podano oprocentowania';	
}

//nie ma sensu walidować dalej gdy brak parametrów
if (empty( $messages )) {
	
	// sprawdzenie, czy $N i $n są liczbami całkowitymi
	if (! is_numeric( $N )) {
		$messages [] = 'Pierwsza wartość nie jest liczbą całkowitą';
	}
	
	if (! is_numeric( $n )) {
		$messages [] = 'Druga wartość nie jest liczbą całkowitą';
	}

	if (! is_numeric( $r )) {
		$messages [] = 'Trzecia wartość nie jest liczbą całkowitą';
	}	

}

// 3. wykonaj zadanie jeśli wszystko w porządku

if (empty ( $messages )) { // gdy brak błędów
	
	//konwersja parametrów na int
	$N = intval($N);
	$n = intval($n);
	$r = intval($r);
	
	$d = 12;
	$e = 1;
	
	//wykonanie operacji
	
	$result = -($N*$r)/($d *($e-(($d/($d+$r))^$n)));
		
	
}

// 4. Wywołanie widoku z przekazaniem zmiennych
// - zainicjowane zmienne ($messages,$N,$n,$operation,$result)
//   będą dostępne w dołączonym skrypcie
include 'calc_view.php';