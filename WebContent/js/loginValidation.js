/**
 * 
 */
$(document).ready(function(){

  jQuery.validator.addMethod("noSpace", function(value, element) { 
  return value.indexOf(" ") < 0 && value != ""; 
});


$(function() {
  
  $("form[name='loginForm']").validate({
   
    rules: {
      
    	name:{required:true,noSpace:true,minlength:5},
      pass: {required:true,noSpace:true},
    
      pass: {
        required: true,
        minlength: 5
      }
     
    },
   
    messages: {
      name:{required:"Please enter UserName",noSpace:"No space please and don't leave it empty",minlength:"userName length must greater than 5"},
      
      pass: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      }
    
    },

    submitHandler: function(form) {
      form.submit();
    }
  });
});
})
