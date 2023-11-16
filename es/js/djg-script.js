import { films } from "../js/stock.js";
/* Small posters generation script using array variables */
// big-box = contenedor general
// popular-row = contenedor populares
// newest-row = contener nuevos
// en primer lugar se renderiza la caja de populares con la de nuevos. Luego al llamar un filtro se borra y se rellena la caja de bigbox.

<<<<<<< HEAD
var bigbox = document.querySelector('#big-box'); // Selecciona la caja general
var popular = document.querySelector('#popular-row'); // Selecciona el div de populares
var newest = document.querySelector('#newest-row'); // Selecciona el div de novedades
var explore = document.querySelector('#explore-row'); // Selecciona el div de explorar
var minimalPopularity = 10;
var movieGenre;  
var movieType = undefined;
var recentYear = 2015;
=======
var newest = document.querySelector('.movie-box-container'); // Selecciona un elemento.

var movieGenre;  
var movieType;
>>>>>>> G5

function filterGenre(genre,){
    movieGenre = genre
}
function filterType(type){
    movieType = type
}

<<<<<<< HEAD
//LOS MAS NUEVOS + LO MAS POPULAR + GENERAL
for (var i in films) {  /* por cada clave presente en films hace un ciclo.*/


    // Rellena recientes.
    if (films[i].release >= recentYear) {
        console.log(films[i].release)

    fetch = newest.innerHTML; /* Selecciona el contenido actual del contenedor "movie-box-container".*/
    
    /* le agrega este HTML con valores extraidos de films*/
    newest.innerHTML = ` 
    <div id="newestFilmCard${i}" class="boxes">
        <div class="box-content">
                <div class="box-content-son" onclick="mostrarPopup(${i})">
                    <h5 class="titulopelicula">${films[i].title}</h5>
                    <p class="duracion">
                    ${films[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${films[i].release}
                    </p> 
                </div> 
            </div>
        </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

    /*aplica una imagen de fondo a los contenedores*/
    var bgimg = document.getElementById(`newestFilmCard${i}`)
    bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
    }

    // rellena populares.
    if (films[i].popindex <= minimalPopularity) {
        console.log(films[i].release)

    fetch = popular.innerHTML; /* Selecciona el contenido actual del contenedor "movie-box-container".*/
    
    /* le agrega este HTML con valores extraidos de films*/
    popular.innerHTML = ` 
    <div id="popularFilmCard${i}" class="boxes">
        <div class="box-content">
                <div class="box-content-son" onclick="mostrarPopup(${i})">
                    <h5 class="titulopelicula">${films[i].title}</h5>
                    <p class="duracion">
                    ${films[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${films[i].release}
                    </p> 
                </div> 
            </div>
        </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

    /*aplica una imagen de fondo a los contenedores*/
    var bgimg = document.getElementById(`popularFilmCard${i}`)
    bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;    
    }

    fetch = explore.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
        
        /* le agrega este HTML con valores extraidos de films*/
        explore.innerHTML = ` 
=======
function setFilterValue() { 

    document.querySelector('.movie-box-container').innerHTML = "" //Cada vez que se llama la funcion borra el contenido del contenedor 'movie-box-container'.


    for (var i in films) {  /* por cada clave presente en films hace un ciclo.*/

        if ( (films[i].genre1 == movieGenre) && (films[i].type == movieType) ){ /* Test de variables con genero y tipo */

            var fetch = document.querySelector('.movie-box-container').innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
            
            /* le agrega este HTML con valores extraidos de films*/
            newest.innerHTML = ` 
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="mostrarPopup(${i})">
                            <h5 class="titulopelicula">${films[i].title}</h5>
                            <p class="duracion">
                            ${films[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${films[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

            /*aplica una imagen de fondo a los contenedores*/
            var bgimg = document.getElementById(`cards${i}`)
            bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
        }        
    }
}

function showAll() {
    document.querySelector('.movie-box-container').innerHTML = "" //Cada vez que se llama la funcion borra el contenido del contenedor 'movie-box-container'.

    for (var i in films) {  /* por cada clave presente en films hace un ciclo.*/
        var fetch = document.querySelector('.movie-box-container').innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
        
        /* le agrega este HTML con valores extraidos de films*/
        newest.innerHTML = ` 
>>>>>>> G5
        <div id="cards${i}" class="boxes">
            <div class="box-content">
                    <div class="box-content-son" onclick="mostrarPopup(${i})">
                        <h5 class="titulopelicula">${films[i].title}</h5>
                        <p class="duracion">
                        ${films[i].duration}
                        </p>
                        <p class="fechasalida">
                        ${films[i].release}
                        </p> 
                    </div> 
                </div>
            </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

        /*aplica una imagen de fondo a los contenedores*/
        var bgimg = document.getElementById(`cards${i}`)
<<<<<<< HEAD
        bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
   
}

function setFilterValue() { 

    //document.querySelector('.movie-box-container').innerHTML = "" //Cada vez que se llama la funcion borra el contenido del contenedor 'movie-box-container'.

    bigbox.innerHTML="";

    for (var i in films) {  /* por cada clave presente en films hace un ciclo.*/      

        if ( (movieGenre == undefined || films[i].genre1 == movieGenre) && (movieType == undefined || films[i].type == movieType) ){ /* Test de variables con genero y tipo */

            fetch = bigbox.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
            
            /* le agrega este HTML con valores extraidos de films*/
            bigbox.innerHTML = ` 
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="mostrarPopup(${i})">
                            <h5 class="titulopelicula">${films[i].title}</h5>
                            <p class="duracion">
                            ${films[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${films[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

            /*aplica una imagen de fondo a los contenedores*/
            var bgimg = document.getElementById(`cards${i}`)
            bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
        }        
    }
}

function showAll() { 

    bigbox.innerHTML="";

    for (var i in films) {  /* por cada clave presente en films hace un ciclo.*/

        /* Test de variables con genero y tipo */

            fetch = bigbox.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
            
            /* le agrega este HTML con valores extraidos de films*/
            bigbox.innerHTML = ` 
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="mostrarPopup(${i})">
                            <h5 class="titulopelicula">${films[i].title}</h5>
                            <p class="duracion">
                            ${films[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${films[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

            /*aplica una imagen de fondo a los contenedores*/
            var bgimg = document.getElementById(`cards${i}`)
            bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
              
=======
        bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;    
>>>>>>> G5
    }
}

document.getElementById('actionButton').addEventListener('click', function() { filterGenre("Acción"); setFilterValue();}); // Event listener que contiene una función que invoca otra función para psarale parametros. 
document.getElementById('adventureButton').addEventListener('click', function() { filterGenre("Aventura"); setFilterValue(); });
document.getElementById('dramaButton').addEventListener('click', function() { filterGenre("Drama"); setFilterValue(); });   // No se pueden pasar parametros sin esta guarrada.
document.getElementById('comedyButton').addEventListener('click', function() { filterGenre("Comedia"); setFilterValue(); });
document.getElementById('scifiButton').addEventListener('click', function() { filterGenre("Ciencia Ficción"); setFilterValue(); }); 
document.getElementById('fantasyButton').addEventListener('click', function() { filterGenre("Fantasía"); setFilterValue(); });
document.getElementById('thrillerButton').addEventListener('click', function() { filterGenre("Suspense"); setFilterValue(); });
document.getElementById('horrorButton').addEventListener('click', function() { filterGenre("Terror"); setFilterValue(); });
document.getElementById('romanceButton').addEventListener('click', function() { filterGenre("Romance"); setFilterValue(); }); 
<<<<<<< HEAD
document.getElementById('allGenreButton').addEventListener('click', function() { filterGenre(undefined); setFilterValue();});
=======
>>>>>>> G5


document.getElementById('liveButton').addEventListener('click', function() { filterType("Live action"); setFilterValue();}); // Event listener que contiene una función que invoca otra función para psarale parametros. 
document.getElementById('animationButton').addEventListener('click', function() { filterType("Animación"); setFilterValue();}); // Event listener que contiene una función que invoca otra función para psarale parametros. 
<<<<<<< HEAD
document.getElementById('allTypeButton').addEventListener('click', function() { filterType(undefined); setFilterValue();});
=======
/*document.getElementById('allTypeButton').addEventListener('click', function() { filterType(); setFilterValue();});  BOTON DESACTIVADO POR AHORA*/
>>>>>>> G5

document.getElementById('cleanFilterButton').addEventListener('click', showAll) /* BOTON DE LIMPIAR FILTRO */