<?php
require_once dirname(__FILE__).'/../config.php';

// KONTROLER strony kalkulatora

// W kontrolerze niczego nie wysyła się do klienta.
// Wysłaniem odpowiedzi zajmie się odpowiedni widok.
// Parametry do widoku przekazujemy przez zmienne.

//ochrona kontrolera - poniższy skrypt przerwie przetwarzanie w tym punkcie gdy użytkownik jest niezalogowany
include _ROOT_PATH.'/app/security/check.php';

//pobranie parametrów
function getParams(&$N,&$n,&$r){
	$N = isset($_REQUEST['N']) ? $_REQUEST['N'] : null;
	$n = isset($_REQUEST['n']) ? $_REQUEST['n'] : null;
	$r = isset($_REQUEST['r']) ? $_REQUEST['r'] : null;	
}

//walidacja parametrów z przygotowaniem zmiennych dla widoku
function validate(&$N,&$n,&$r,&$messages){
	// sprawdzenie, czy parametry zostały przekazane
	if ( ! (isset($N) && isset($n) && isset($r))) {
		// sytuacja wystąpi kiedy np. kontroler zostanie wywołany bezpośrednio - nie z formularza
		// teraz zakładamy, ze nie jest to błąd. Po prostu nie wykonamy obliczeń
		return false;
	}

	// sprawdzenie, czy potrzebne wartości zostały przekazane
	if ( $N == "") {
		$messages [] = 'Nie podano Kwoty kredytu';
	}
	if ( $n == "") {
		$messages [] = 'Nie podano ilości rat';
	}
	if ( $r == "") {
		$messages [] = 'Nie podano oprocentowania';
	}

	//nie ma sensu walidować dalej gdy brak parametrów
	if (count ( $messages ) != 0) return false;
	
	// sprawdzenie, czy $x i $y są liczbami całkowitymi
	if (! is_numeric( $N )) {
		$messages [] = 'Pierwsza wartość nie jest liczbą całkowitą';
	}
	
	if (! is_numeric( $n )) {
		$messages [] = 'Druga wartość nie jest liczbą całkowitą';
	}
	
	if (! is_numeric( $r )) {
		$messages [] = 'Druga wartość nie jest liczbą całkowitą';
	}	

	if (count ( $messages ) != 0) return false;
	else return true;
}

function process(&$N,&$n,&$r,&$messages,&$result){
	global $role;
	
	//konwersja parametrów na int
	$N = intval($N);
	$n = intval($n);
	$r = intval($r);
	
	$d = 12;
	$e = 1;
	
	//wykonanie operacji
	if($role == 'user' && $N > 50000){
		$messages [] = 'Maksymalna kwota kredytu dla użytkownika to 50tys!';
	}
	else {
		$result = -($N*$r)/($d *($e-(($d/($d+$r))^$n)));
	}
	
}

//definicja zmiennych kontrolera
$N = null;
$n = null;
$r = null;
$result = null;
$messages = array();

//pobierz parametry i wykonaj zadanie jeśli wszystko w porządku
getParams($N,$n,$r);
if ( validate($N,$n,$r,$messages) ) { // gdy brak błędów
	process($N,$n,$r,$messages,$result);
}

// Wywołanie widoku z przekazaniem zmiennych
// - zainicjowane zmienne ($messages,$x,$y,$r,$result)
//   będą dostępne w dołączonym skrypcie
include 'calc_view.php';