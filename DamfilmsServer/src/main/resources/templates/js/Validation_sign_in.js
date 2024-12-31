
function validarFormulario() {
  // Obtener los valores de los campos
  var email = document.getElementById('email').value;
  var nombre = document.getElementById('nombre').value;
  var password = document.getElementById('password').value;

  // Expresiones regulares para validaciones
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  var nombreRegex = /^[a-zA-Z]{3,}$/;
  var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{5,}$/;

  
  // Validar nombre
  if (!nombreRegex.test(nombre)) {
    document.getElementById('nombre-error').innerHTML = 'El nombre debe tener al menos 3 letras.';
    return false;
} else {
    document.getElementById('nombre-error').innerHTML = '';
}

  // Validar email
  if (!emailRegex.test(email)) {
      document.getElementById('email-error').innerHTML = 'Ingrese un email válido.';
      return false;
  } else {
      document.getElementById('email-error').innerHTML = '';
  }

  // Validar contraseña
  if (!passwordRegex.test(password)) {
      document.getElementById('password-error').innerHTML = 'La contraseña debe contener al menos una mayúscula, una minúscula, un número y un símbolo especial, y tener al menos 5 caracteres.';
      return false;
  } else {
      document.getElementById('password-error').innerHTML = '';
  }

  // Redirigir a la página home
  window.location.href= "home.html";

}
//Boton para ocultar o ver la contraseña
function togglePasswordVisibility() {
  var passwordInput = document.getElementById('password');
  var showPasswordButton = document.getElementById('show-password');

  if (passwordInput.type === 'password') {
    passwordInput.type = 'text';
  } else {
    passwordInput.type = 'password';
  }
}