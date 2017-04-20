/**this is for registratio validation
 * 
 */
$(document).ready(function(){

  jQuery.validator.addMethod("noSpace", function(value, element) { 
  return value.indexOf(" ") < 0 && value != ""; 
});
 
  jQuery.validator.addMethod("phoneno", function(value, element) {
	  value = value.replace(/\s+/g, "");
	    return this.optional(element) || value.length == 10 && 
	    value.match("[0-9]");
	}, "<br />Please specify a valid phone number");
  
  $(function () {
	  $('#mobileNumber').keydown(function (e) {
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
  
  $("form[name='registerForm']").validate({
   
    rules: {
      
    	name:{required:true,noSpace:true,minlength:5},
      pass: {required:true,noSpace:true},
    
      pass: {
        required: true,
        minlength: 5
      },
      confirmPassword:{
    	  minlength: 5,
    	  required:true,
    	  equalTo:"#pass"
      },
      mobileNumber:{
    	  required:true,
          phoneno:true
      },
      gender:{
    	  required:true
      }
      
    },
   
    messages: {
      name:{required:"Please enter UserName",noSpace:"No space please and don't leave it empty",minlength:"userName length must greater than 5"},
      
      pass: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      },
      confirmPassword:{
    	required:"Please provide a confirm password",
    	equalTo:"password must be same"
      },
      mobileNumber:{
    	  required:"please provide a mobile number"
      }
      
     
    },

    submitHandler: function(form) {
      form.submit();
    }
  });
});
})
