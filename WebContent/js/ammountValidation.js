/**
 * 
 */

/**
 * 
 */

/**this is for profile validation
 * 
 */
$(document).ready(function () {
  //called when key is pressed in textbox
  $("#ammount").keypress(function (e) {
     //if the letter is not digit then display error and don't type anything
     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
               return false;
    }
   });
  
  $(function () {
	  alert(1);
	  $('#ammount').keydown(function (e) {
	  if (e.shiftKey || e.ctrlKey || e.altKey) {
	  e.preventDefault();
	  } else {
	  var key = e.keyCode;
	  if (!((key == 8) || (key == 46) || (key >= 35 && key <= 40) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
	  e.preventDefault();
	  }
	  }
	  });
	  });


$(function() {
  
  $("form[name='transactionForm']").validate({
   
    rules: {
      
    	
      
    },
   
    messages: {
     
      
     
    },

    submitHandler: function(form) {
      form.submit();
    }
  });
});
});
