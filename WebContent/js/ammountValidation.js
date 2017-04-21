/**this is for profile validation
 * 
 */
$(document).ready(function () {
  //called when key is pressed in textbox
  
	$("#ammount").keypress(function (event) {
		
        return isNumber(event, this)
    });
	
	function isNumber(evt,element){
		var charCode = (evt.which) ? evt.which : event.keyCode

		        if (
		                                                                            // “-” CHECK MINUS, AND ONLY ONE.
		            (charCode != 46 || $(element).val().indexOf('.') != -1) &&      // “.” CHECK DOT, AND ONLY ONE.
		            (charCode < 48 || charCode > 57))
		            return false;

		        return true;
	}
	
$(function() {
  
  $("#transactionForm").validate({
  
    messages: {
     
    	accountNumber:{required:"please select account number"},
    	
    	anotherAccountNumber:{required:"please select account number"}
     
    },

    submitHandler: function(form) {
      form.submit();
    }
  });
});
});
