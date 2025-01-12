 async function obtenerTiposSuscripciones() {
            try {
                const response = await fetch('/suscripciones/tipos');
                if (!response.ok) {
                    throw new Error('Error al obtener los tipos de suscripción');
                }
                const tiposSuscripcion = await response.json();
                const select = document.getElementById('roles');
                if (tiposSuscripcion && tiposSuscripcion.length > 0) {
                    select.innerHTML = '';
                    tiposSuscripcion.forEach(tipo => {
                        const option = document.createElement('option');
                        option.value = tipo;
                        option.textContent = tipo.toUpperCase();
                        select.appendChild(option);
                    });
                } else {
                    const option = document.createElement('option');
                    option.value = '';
                    option.textContent = 'No hay tipos de suscripción disponibles';
                    select.appendChild(option);
                }
            } catch (error) {
                console.error('Error al obtener los tipos de suscripción:', error);
            }
        }


window.onload = obtenerTiposSuscripciones;
        
function validarFormulario() {
  // Obtener los valores de los campos
  var email = document.getElementById('email').value;
  var nombre = document.getElementById('nombre').value;
  var password = document.getElementById('password').value;

  // Expresiones regulares para validaciones
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  var nombreRegex = /^[a-zA-Z]{3,}$/;
  var passwordRegex = /[a-zA-Z0-9]{3,8}/;

  
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