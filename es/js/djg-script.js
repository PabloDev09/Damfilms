import { films } from "../res/stock.js";
/* Small posters generation script using array variables */

var newest = document.querySelector('.movie-box-container');
for (var i = 0; i < films.length; i++) {
    var fetch = document.querySelector('.movie-box-container').innerHTML;
    newest.innerHTML = 
    `
    <div id="cards${i}"class="boxes">
        <div class="box-content">
            <!--<a href="#"><img class="box-img"></a>-->
            
            <div class="box-content-son">
                <h5 class="titulopelicula">${films[i].titulo}</h5>
                <p class="duracion">
                ${films[i].duracion}
                </p>
                <p class="fechasalida">
                ${films[i].fecha_lanzamiento}
                </p> 
                </div> 

            </div>
        </div>` + fetch;  
        var bgimg = document.getElementById(`cards${i}`)
        bgimg.style.backgroundImage = `url('img/170x240/${films[i].genero}/${films[i].poster}.png')`;
} 


/*src="img/170x240/${films[i].genero}/${films[i].poster}.png"*/