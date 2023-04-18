<?php
// KONTROLER strony kalkulatora
require_once dirname(__FILE__).'/../config.php';
//załaduj Smarty
require_once _ROOT_PATH.'/lib/smarty/Smarty.class.php';

//pobranie parametrów
function getParams(&$form){
	$form['N'] = isset($_REQUEST['N']) ? $_REQUEST['N'] : null;
	$form['n'] = isset($_REQUEST['n']) ? $_REQUEST['n'] : null;
	$form['r'] = isset($_REQUEST['r']) ? $_REQUEST['r'] : null;	
}

//walidacja parametrów z przygotowaniem zmiennych dla widoku
function validate(&$form,&$infos,&$msgs,&$hide_intro){

	//sprawdzenie, czy parametry zostały przekazane - jeśli nie to zakończ walidację
	if ( ! (isset($form['N']) && isset($form['n']) && isset($form['r']) ))	return false;	
	
	//parametry przekazane zatem
	//nie pokazuj wstępu strony gdy tryb obliczeń (aby nie trzeba było przesuwać)
	// - ta zmienna zostanie użyta w widoku aby nie wyświetlać całego bloku itro z tłem 
	$hide_intro = true;

	$infos [] = 'Przekazano parametry.';

	// sprawdzenie, czy potrzebne wartości zostały przekazane
	if ( $form['N'] == "") $msgs [] = 'Nie podano kwoty kredytu';
	if ( $form['n'] == "") $msgs [] = 'Nie podano ilości rat';
	if ( $form['r'] == "") $msgs [] = 'Nie podano oprocentowania';
	
	//nie ma sensu walidować dalej gdy brak parametrów
	if ( count($msgs)==0 ) {
		// sprawdzenie, czy $N, $n i $r są liczbami całkowitymi
		if (! is_numeric( $form['N'] )) $msgs [] = 'Pierwsza wartość nie jest liczbą';
		if (! is_numeric( $form['n'] )) $msgs [] = 'Druga wartość nie jest liczbą';
		if (! is_numeric( $form['r'] )) $msgs [] = 'Trzecia wartość nie jest liczbą';
	}
	
	if (count($msgs)>0) return false;
	else return true;
}
	
// wykonaj obliczenia
function process(&$form,&$infos,&$msgs,&$result){
	$infos [] = 'Parametry poprawne. Wykonuję obliczenia.';
	
	//konwersja parametrów na int
	$form['N'] = floatval($form['N']);
	$form['n'] = floatval($form['n']);
	$form['r'] = floatval($form['r']);
	
	//wykonanie operacji
	
	$d = 12;
	$e = 1;
	
	$result = -($form['N']*$form['r'])/($d *($e-(($d/($d+$form['r']))^$form['n'])));
}

//inicjacja zmiennych
$form = null;
$infos = array();
$messages = array();
$result = null;
$hide_intro = false;
	
getParams($form);
if ( validate($form,$infos,$messages,$hide_intro) ){
	process($form,$infos,$messages,$result);
}

// 4. Przygotowanie danych dla szablonu

$smarty = new Smarty();

$smarty->assign('app_url',_APP_URL);
$smarty->assign('root_path',_ROOT_PATH);
$smarty->assign('page_title','Kalkulator kredytowy');
$smarty->assign('page_description','Szablonowanie oparte na bibliotece Smarty');
$smarty->assign('page_header','Szablony Smarty');

$smarty->assign('hide_intro',$hide_intro);

//pozostałe zmienne niekoniecznie muszą istnieć, dlatego sprawdzamy aby nie otrzymać ostrzeżenia
$smarty->assign('form',$form);
$smarty->assign('result',$result);
$smarty->assign('messages',$messages);
$smarty->assign('infos',$infos);

// 5. Wywołanie szablonu
$smarty->display(_ROOT_PATH.'/app/calc.html');