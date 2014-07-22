function checkForm(form)
  {
  alert("check form");
    if(form.user.username.value == "") {
      alert("Error: Username cannot be blank!");
      form.username.focus();
      return false;
    }
    re = /^\w+$/;
    if(!re.test(form.user.username.value)) {
      alert("Error: Username must contain only letters, numbers and underscores!");
      form.user.username.focus();
      return false;
    }

    if(form.user.password.value != "" && form.user.password.value == form.password2.value) {
      if(form.user.password.value.length < 6) {
        alert("Error: Password must contain at least six characters!");
        form.user.password.focus();
        return false;
      }
      if(form.user.password.value == form.user.username.value) {
        alert("Error: Password must be different from Username!");
        form.user.password.focus();
        return false;
      }
      re = /[0-9]/;
      if(!re.test(form.user.password.value)) {
        alert("Error: password must contain at least one number (0-9)!");
        form.user.password.focus();
        return false;
      }
      }
    } else {
      alert("Error: Please check that you've entered and confirmed your password!");
      form.user.password.focus();
      return false;
    }

    return true;
  }
  
function formSubmit() {
	document.getElementById("logoutForm").submit();
}