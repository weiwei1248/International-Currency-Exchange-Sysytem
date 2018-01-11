var rate = 0;
var receive = 0;
var originC = "";
var destinC = "";

//update
$(document).ready(function () {
	checkUpdate();	
});

function fetch() {
	var amount = document.getElementById("_oamount").value;
	var id1 = document.getElementById("_oaccount").options[document.getElementById("_oaccount").selectedIndex].value;
	var id2 = document.getElementById("_daccount").options[document.getElementById("_daccount").selectedIndex].value;
	var $form = $('#_form');
	$.ajax({
		type: 'GET',
		url: '/transaction/exchangeRate',
		data: {sourceAccount:id1, destinationAccount:id2, sourceAmount:amount},
		dataType: 'json',
		success: function (msg) {
			var jsonText = JSON.stringify(msg);
			var jsonObject = JSON.parse(jsonText);
			rate = jsonObject.exchangeRate;
			document.getElementById("_obankrate").innerHTML = rate
		},
		error:function (msg) {
			return "error";
		}
	});
}

function checkUpdate() {
	receive = document.getElementById("_oamount").value * rate;
	document.getElementById("_damount").value = receive;
	var oselect = document.getElementById("_oaccount");
	var oindex = oselect.selectedIndex;
	originC = oselect.options[oindex].text.split('|')[0];
	document.getElementById("_ocurrency").value = originC;
	var dselect = document.getElementById("_daccount");
	var dindex = dselect.selectedIndex;
	destinC = dselect.options[dindex].text.split("|")[0];
	document.getElementById("_dcurrency").value = destinC;
 
    fetch();
    checkRate();
}

function checkTotal() {
  var total = parseInt(document.getElementById("_oamount").value)*rate;
  document.getElementById("_rate").innerHTML = rate;
  document.getElementById("_total").innerHTML = total;
  document.getElementById("_toaccount").innerHTML = document.getElementById("_oaccount").value.split("|")[0];
  document.getElementById("_tdaccount").innerHTML = document.getElementById("_daccount").value.split("|")[0];
  document.getElementById("_toamount").innerHTML = document.getElementById("_oamount").value;
  document.getElementById("_tdamount").innerHTML = receive;
  document.getElementById("_tocurrency").innerHTML = document.getElementById("_ocurrency").value;
  document.getElementById("_tdcurrency").innerHTML = document.getElementById("_dcurrency").value;
  document.getElementById("_tdcurrency").innerHTML = document.getElementById("_dcurrency").value;
  getOriginalBankImg(document.getElementById("_oaccount").value.split("|")[2]);
  getDestinBankImg(document.getElementById("_daccount").value.split("|")[2]);
}

function checkRate() {
	document.getElementById("_sb").removeAttribute("disabled");
	if(rate == 0) {
		document.getElementById("_sb").setAttribute("disabled",true);
	}
	if(document.getElementById("_oamount").value == 0) {
		document.getElementById("_oamount").focus();
		document.getElementById("_sb").setAttribute("disabled",true);
	}
}

function getOriginalBankImg(type) {
  var stringType = "L1";
  switch(type)
  {
    case "ScotiaBank": {stringType = "img/icon/scotia.png";break;}
    case "CIBC": {stringType = "img/icon/cibc.png";break;}
    case "MBO": {stringType = "img/icon/mbo.png";break;}
    case "TD": {stringType = "img/icon/td.png";break;}
    case "RBC": {stringType = "img/icon/rbc.png";break;}
    case "HSBC": {stringType = "img/icon/hsbc.png";break;}
    default:break;
  }
  document.getElementById("_oImg").src = stringType;
}

function getDestinBankImg(type) {
  var stringType = "L1";
  switch(type)
  {
    case "ScotiaBank": {stringType = "img/icon/scotia.png";break;}
    case "CIBC": {stringType = "img/icon/cibc.png";break;}
    case "MBO": {stringType = "img/icon/mbo.png";break;}
    case "TD": {stringType = "img/icon/ td.png";break;}
    case "RBC": {stringType = "img/icon/rbc.png";break;}
    case "HSBC": {stringType = "img/icon/hsbc.png";break;}
    default:break;
  }
  document.getElementById("_dImg").src = stringType;
}


