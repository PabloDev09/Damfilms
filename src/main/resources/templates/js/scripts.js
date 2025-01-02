import { films } from "../js/stock.js";

// Función para mostrar el popup y los detalles de cada película
export function showPopup(a) {

    for (var i in films) {
    
     popuphtml.innerHTML= `
        <div id="popup" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); padding: 20px; background-color: #fff; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); z-index: 1000;">
            <h2>Film Details</h2>
            <p>Title: ${films[i].title}<br>Director: ${films[i].duration}<br>Year: ${films[i].release}</p>
            <button onclick="closePopup()">Close Popup</button>
        </div>
    `;

    // Agregar el contenido al body
    document.body.insertAdjacentHTML('beforeend', popuphtml);

    // Display the popup
    document.getElementById("popup").style.display = "block";
    
    }//cierra el for

} //cierra la función showPopup


// Función para cerrar el popup
function closePopup() {
    
    var popup = document.getElementById("popup");
    if (popup) {
        popup.parentNode.removeChild(popup);
    }
}
