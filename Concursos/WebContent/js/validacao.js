function FormataCampo(Campo,teclapres,mascara){ 
//pegando o tamanho do texto da caixa de texto com delay de -1 no event 
//ou seja o caractere que foi digitado não será contado. 
strtext = Campo.value 
tamtext = strtext.length 
//pegando o tamanho da mascara 
tammask = mascara.length 
//criando um array para guardar cada caractere da máscara 
arrmask = new Array(tammask) 
//jogando os caracteres para o vetor 
for (var i = 0 ; i < tammask; i++){ 
arrmask[i] = mascara.slice(i,i+1) 
} 
//alert (teclapres.keyCode) 
//começando o trabalho sujo 
if (((((arrmask[tamtext] == "#") || (arrmask[tamtext] == "9"))) || (((arrmask[tamtext+1] != "#") || (arrmask[tamtext+1] != "9"))))){ 
if ((teclapres.keyCode >= 37 && teclapres.keyCode <= 40)||(teclapres.keyCode >= 48 && teclapres.keyCode <= 57)||(teclapres.keyCode >= 96 && teclapres.keyCode <= 105)||(teclapres.keyCode == 8)||(teclapres.keyCode == 9) ||(teclapres.keyCode == 46) ||(teclapres.keyCode == 13)){ 
Organiza_Casa(Campo,arrmask[tamtext],teclapres.keyCode,strtext) 
} 
else{ 
Detona_Event(Campo,strtext) 
} 
} 
else{//Aqui funcionaria a mascara para números mas eu ainda não implementei 
if ((arrmask[tamtext] == "A")) { 
charupper = event.valueOf() 
//charupper = charupper.toUpperCase() 
Detona_Event(Campo,strtext) 
masktext = strtext + charupper 
Campo.value = masktext 
} 
} 
} 
function Organiza_Casa(Campo,arrpos,teclapres_key,strtext){ 
if (((arrpos == "/") || (arrpos == ".") || (arrpos == ",") || (arrpos == ":") || (arrpos == " ") || (arrpos == "-")) && !(teclapres_key == 8)){ 
separador = arrpos 
masktext = strtext + separador 
Campo.value = masktext 
} 
} 
function Detona_Event(Campo,strtext){ 
event.returnValue = false 
if (strtext != "") { 
Campo.value = strtext 
} 
}

function BoxFormat(objForm, strField, strfull, sMask) {
      var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;

      sValue = strfull;

      sValue = sValue.toString().replace( "-", "" );
      sValue = sValue.toString().replace( "-", "" );
      sValue = sValue.toString().replace( ".", "" );
      sValue = sValue.toString().replace( ".", "" );
      sValue = sValue.toString().replace( "/", "" );
      sValue = sValue.toString().replace( "/", "" );
      sValue = sValue.toString().replace( "(", "" );
      sValue = sValue.toString().replace( "(", "" );
      sValue = sValue.toString().replace( ")", "" );
      sValue = sValue.toString().replace( ")", "" );
      sValue = sValue.toString().replace( " ", "" );
      sValue = sValue.toString().replace( " ", "" );
      fldLen = sValue.length;
      mskLen = sMask.length;

      i = 0;
      nCount = 0;
      sCod = "";
      mskLen = fldLen;

      while (i <= mskLen) {
        bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/"))
        bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))

        if (bolMask) {
          sCod += sMask.charAt(i);
          mskLen++; }
        else {
          sCod += sValue.charAt(nCount);
          nCount++;
        }

        i++;
      }

      objForm[strField].value = sCod;

      if (nTecla != 8) { // backspace
        if (sMask.charAt(i-1) == "9") { // apenas números...
          return ((nTecla > 47) && (nTecla < 58)); } // números de 0 a 9
        else { // qualquer caracter...
          return true;
        } }
      else {
        return true;
      }
}

function txtBoxFormat(objForm, strField, sMask, evtKeyPress) {
      var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;

      if(document.all) {
        nTecla = evtKeyPress.keyCode; }
      else if(document.layers) {
        nTecla = evtKeyPress.which;
      }

      sValue = objForm[strField].value;

      sValue = sValue.toString().replace( "-", "" );
      sValue = sValue.toString().replace( "-", "" );
      sValue = sValue.toString().replace( ".", "" );
      sValue = sValue.toString().replace( ".", "" );
      sValue = sValue.toString().replace( "/", "" );
      sValue = sValue.toString().replace( "/", "" );
      sValue = sValue.toString().replace( "(", "" );
      sValue = sValue.toString().replace( "(", "" );
      sValue = sValue.toString().replace( ")", "" );
      sValue = sValue.toString().replace( ")", "" );
      sValue = sValue.toString().replace( " ", "" );
      sValue = sValue.toString().replace( " ", "" );
      fldLen = sValue.length;
      mskLen = sMask.length;

      i = 0;
      nCount = 0;
      sCod = "";
      mskLen = fldLen;

      while (i <= mskLen) {
        bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/"))
        bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))

        if (bolMask) {
          sCod += sMask.charAt(i);
          mskLen++; }
        else {
          sCod += sValue.charAt(nCount);
          nCount++;
        }

        i++;
      }

      objForm[strField].value = sCod;

      if (nTecla != 8) { // backspace
        if (sMask.charAt(i-1) == "9") { // apenas números...
          return ((nTecla > 47) && (nTecla < 58)); } // números de 0 a 9
        else { // qualquer caracter...
          return true;
        } }
      else {
        return true;
      }
}