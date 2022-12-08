
$(document).ready(function()
{
	$('#leftpanel').hide();
	$('#rightpanel').empty().load('html/overview.html');
});

function beautify() {

	var ta = document.getElementById('j_id_m:ta'),
	    cp = "";
		
	cp = cp.replace(/\'/g,'').replace(/\"/g,'');
	
	if ( !isNaN(parseInt(cp)) ) {  // argument is integer
		cp = parseInt(cp);
	} else {
		cp = cp ? cp : 4;
	}
	

		ta.value =  vkbeautify.xml(ta.value,cp);

}

function minify() {
	var ta = document.getElementById('ta');
	var preservecomm = document.getElementById('preservews').checked;
	
		ta.value = vkbeautify.xmlmin(ta.value,true) 
	
	
}


