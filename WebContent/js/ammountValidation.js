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
