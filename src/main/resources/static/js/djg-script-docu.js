import {documentals} from "./stock.js";
/* Small posters generation script using array variables */
// big-box = contenedor general
// popular-row = contenedor populares
// newest-row = contener nuevos
// en primer lugar se renderiza la caja de populares con la de nuevos. Luego al llamar un filtro se borra y se rellena la caja de bigbox.

var bigbox = document.querySelector('#big-box'); // Selecciona la caja general
var popular = document.querySelector('#popular-row'); // Selecciona el div de populares
var newest = document.querySelector('#newest-row'); // Selecciona el div de novedades
var explore = document.querySelector('#explore-row'); // Selecciona el div de explorar
var minimalPopularity = 9;
var movieGenre;  
var movieType = undefined;
var recentYear = 2015;

function filterGenre(genre){
    movieGenre = genre
}
function filterType(type){
    movieType = type
}

//LOS MAS NUEVOS + LO MAS POPULAR + GENERAL
for (var i in documentals) {  /* por cada clave presente en films hace un ciclo.*/

    // Rellena recientes.
    if (documentals[i].release >= recentYear) {
        console.log(documentals[i].release)

    fetch = newest.innerHTML; /* Selecciona el contenido actual del contenedor "movie-box-container".*/
    
    /* le agrega este HTML con valores extraidos de films*/
    newest.innerHTML = ` 
    <div id="newestFilmCard${i}" class="boxes">
        <div class="box-content">
                <div class="box-content-son" onclick="showPopup(${i})">
                    <h5 class="titulopelicula">${documentals[i].title}</h5>
                    <p class="duracion">
                    ${documentals[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${documentals[i].release}
                    </p> 
                </div> 
            </div>
        </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

    /*aplica una imagen de fondo a los contenedores*/
    var bgimg = document.getElementById(`newestFilmCard${i}`)
    bgimg.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;
    }

    // rellena populares.
    if (documentals[i].popindex <= minimalPopularity) {
        console.log(documentals[i].release)

    fetch = popular.innerHTML; /* Selecciona el contenido actual del contenedor "movie-box-container".*/
    
    /* le agrega este HTML con valores extraidos de films*/
    popular.innerHTML = ` 
    <div id="popularFilmCard${i}" class="boxes">
        <div class="box-content">
                <div class="box-content-son" onclick="showPopup(${i})">
                    <h5 class="titulopelicula">${documentals[i].title}</h5>
                    <p class="duracion">
                    ${documentals[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${documentals[i].release}
                    </p> 
                </div> 
            </div>
        </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

    /*aplica una imagen de fondo a los contenedores*/
    var bgimg = document.getElementById(`popularFilmCard${i}`)
    bgimg.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;    
    }

    fetch = explore.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
        
        /* le agrega este HTML con valores extraidos de films*/
        explore.innerHTML = ` 
        <div id="cards${i}" class="boxes">
            <div class="box-content">
                    <div class="box-content-son" onclick="showPopup(${i})">
                        <h5 class="titulopelicula">${documentals[i].title}</h5>
                        <p class="duracion">
                        ${documentals[i].duration}
                        </p>
                        <p class="fechasalida">
                        ${documentals[i].release}
                        </p> 
                    </div> 
                </div>
            </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

        /*aplica una imagen de fondo a los contenedores*/
        var bgimg = document.getElementById(`cards${i}`)
        bgimg.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;
   
}

function setFilterValue() { 

    //document.querySelector('.movie-box-container').innerHTML = "" //Cada vez que se llama la funcion borra el contenido del contenedor 'movie-box-container'.

    bigbox.innerHTML="";

    for (var i in documentals) {  /* por cada clave presente en films hace un ciclo.*/      

        if ( (movieGenre == undefined || documentals[i].genre1 == movieGenre) && (movieType == undefined || documentals[i].type == movieType) ){ /* Test de variables con genero y tipo */

            fetch = bigbox.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
            
            /* le agrega este HTML con valores extraidos de films*/
            bigbox.innerHTML = ` 
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="showPopup(${i})">
                            <h5 class="titulopelicula">${documentals[i].title}</h5>
                            <p class="duracion">
                            ${documentals[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${documentals[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

            /*aplica una imagen de fondo a los contenedores*/
            var bgimg = document.getElementById(`cards${i}`)
            bgimg.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;
        }        
    }
}

function showAll() { 

    bigbox.innerHTML="";

    for (var i in documentals) {  /* por cada clave presente en films hace un ciclo.*/

        /* Test de variables con genero y tipo */

            fetch = bigbox.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
            
            /* le agrega este HTML con valores extraidos de films*/
            bigbox.innerHTML = ` 
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="showPopup(${i})">
                            <h5 class="titulopelicula">${documentals[i].title}</h5>
                            <p class="duracion">
                            ${documentals[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${documentals[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

            /*aplica una imagen de fondo a los contenedores*/
            var bgimg = document.getElementById(`cards${i}`)
            bgimg.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;         
    }
}

document.getElementById('historicButton').addEventListener('click', function() { filterGenre("Histórico"); setFilterValue();}); // Event listener que contiene una función que invoca otra función para psarale parametros. 
document.getElementById('biographicButton').addEventListener('click', function() { filterGenre("Biográfico"); setFilterValue(); });
document.getElementById('scienceButton').addEventListener('click', function() { filterGenre("Ciencia"); setFilterValue(); });   // No se pueden pasar parametros sin esta guarrada.
document.getElementById('denounceButton').addEventListener('click', function() { filterGenre("Denuncia"); setFilterValue(); });
document.getElementById('reportButton').addEventListener('click', function() { filterGenre("Reportaje"); setFilterValue(); }); 
document.getElementById('showAllButton').addEventListener('click', function() { filterGenre(undefined); setFilterValue();});
