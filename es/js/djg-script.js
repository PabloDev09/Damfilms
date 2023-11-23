import { films, films_sinopsis } from "../js/stock.js";
/* Small posters generation script using array variables */
// big-box = contenedor general
// popular-row = contenedor populares
// newest-row = contener nuevos
// en primer lugar se renderiza la caja de populares con la de nuevos. Luego al llamar un filtro se borra y se rellena la caja de bigbox.

var bigbox = document.querySelector('#big-box'); // Selecciona la caja general
var popular = document.querySelector('#popular-row'); // Selecciona el div de populares
var newest = document.querySelector('#newest-row'); // Selecciona el div de novedades
var explore = document.querySelector('#explore-row'); // Selecciona el div de explorar
var modalBox = document.querySelector('#modalBox'); // Selecciona el div de explorar
var minimalPopularity = 9;
var movieGenre;  
var movieType = undefined;
var recentYear = 2016;

function filterGenre(genre){
    movieGenre = genre
}
function filterType(type){
    movieType = type
}

//LOS MAS NUEVOS + LO MAS POPULAR + GENERAL
for (var i in films) {  /* por cada clave presente en films hace un ciclo.*/


    // Rellena recientes.
    if (films[i].release >= recentYear) {
        console.log(films[i].release)

    fetch = newest.innerHTML; /* Selecciona el contenido actual del contenedor "movie-box-container".*/
    
    /* le agrega este HTML con valores extraidos de films*/
    newest.innerHTML = ` 
    <!-- LLAMADA AL MODAL -->
    <button type="button" class="sin-borde" data-bs-toggle="modal" data-bs-target="#showcase${i}">
    <div id="newestFilmCard${i}" class="boxes">
        <div class="box-content">
                <div class="box-content-son">
                    <h5 class="titulopelicula">${films[i].title}</h5>
                    <p class="duracion">
                    ${films[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${films[i].release}
                    </p> 
                </div> 
            </div>
        </div>
        </button>
        ` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

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
    <button type="button" class="sin-borde" data-bs-toggle="modal" data-bs-target="#showcase${i}">
    <div id="popularFilmCard${i}" class="boxes">
        <div class="box-content">
                <div class="box-content-son">
                    <h5 class="titulopelicula">${films[i].title}</h5>
                    <p class="duracion">
                    ${films[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${films[i].release}
                    </p> 
                </div> 
            </div>
        </div>
        </button>
        ` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

    /*aplica una imagen de fondo a los contenedores*/
    var bgimg = document.getElementById(`popularFilmCard${i}`)
    bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;    
    }

    fetch = explore.innerHTML;  /* Selecciona el contenido actual del contenedor "movie-box-container".*/
        
        /* le agrega este HTML con valores extraidos de films*/
        explore.innerHTML = ` 

        <button type="button" class="sin-borde" data-bs-toggle="modal" data-bs-target="#showcase${i}">
        <div id="cards${i}" class="boxes">
            <div class="box-content">
                    <div class="box-content-son" onclick="showPopup(${i})">
                        <h5 class="titulopelicula">${films[i].title}</h5>
                        <p class="duracion">
                        ${films[i].duration}
                        </p>
                        <p class="fechasalida">
                        ${films[i].release}
                        </p> 
                    </div> 
                </div>
            </div>
            </button>      
            ` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

        /*aplica una imagen de fondo a los contenedores*/
        var bgimg = document.getElementById(`cards${i}`)
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
            <button type="button" class="sin-borde" data-bs-toggle="modal" data-bs-target="#showcase${i}">
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="showPopup(${i})">
                            <h5 class="titulopelicula">${films[i].title}</h5>
                            <p class="duracion">
                            ${films[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${films[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>
                </button>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

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
            <button type="button" class="sin-borde" data-bs-toggle="modal" data-bs-target="#showcase${i}">
            <div id="cards${i}" class="boxes">
                <div class="box-content">
                        <div class="box-content-son" onclick="showPopup(${i})">
                            <h5 class="titulopelicula">${films[i].title}</h5>
                            <p class="duracion">
                            ${films[i].duration}
                            </p>
                            <p class="fechasalida">
                            ${films[i].release}
                            </p> 
                        </div> 
                    </div>
                </div>
                </button>` + fetch; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/

            /*aplica una imagen de fondo a los contenedores*/
            var bgimg = document.getElementById(`cards${i}`)
            /* var pepe = bgimg.getchildren("box-content-son")
            pepe.addEventListener()*/
            bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
              
    }
}



// ESTE BUCLE FOR SOLO SIRVE PARA DIBUJAR LOS MODALES OCULTOS HASTA SU LLAMADA.
for (var i in films) { 

    var fetchy = modalBox.innerHTML; /* Selecciona el contenido actual del contenedor "movie-box-container".*/
    
    /* le agrega este HTML con valores extraidos de films*/
    modalBox.innerHTML = ` 
    
    <!-- MODAL QUE SE DIBUJA -->

    <div class="modal fade" id="showcase${i}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> <!-- nombre al que responde -->
    
        <div class="modal-dialog modal-lg"> <!-- modo de respuesta -->
    
            <div class="modal-content" style ="background: rgb(0,0,0); background: linear-gradient(180deg, rgba(32,32,32,1) 35%, rgba(84,84,84,1) 100%);"> <!-- caja de contenido -->
                    
                <!-- CUERPO REAL DEL FOOTER (OBLIGATORIO) -> SI BORRAS PIERDES TODO -->
                <div class="modal-body">

                <div id="body">

        <header id="header">

              <br>

                <h1><font size="100" color="white">${films[i].title}</font></h1>
                <h2><font color="FF6A1A">${films[i].genre1}</font></h2>
        </header>

        <section id="sinopsis">
            <p><font color ="white">${films_sinopsis[i].sinopsis}</font></p>
        </section>

        <section id="image">
            <img src="img/posters/films/${films[i].poster}.jpg" align="left" width="300px" height="550" id="image">
        </section>

        <br>

        <section id="buttons" align="center">
            <input type="button" id="button" value="Ver ahora"/>
            <input type="button" id="button" value="Seguir viendo"/>
        </section>

            </div> 
                      
                </div>

            </div>
        </div> <!-- CIERRA EL DIALOGO MODAL -->
    </div> <!-- CIERRA EL MODAL ENTERO -->
        ` + fetchy; /* se actualiza el contenido conocido. Si antes habia 0 elementos, lo lee y sabrá que ahora hay 1 mas.*/
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
document.getElementById('allGenreButton').addEventListener('click', function() { filterGenre(undefined); setFilterValue();});


document.getElementById('liveButton').addEventListener('click', function() { filterType("Live action"); setFilterValue();}); // Event listener que contiene una función que invoca otra función para psarale parametros. 
document.getElementById('animationButton').addEventListener('click', function() { filterType("Animación"); setFilterValue();}); // Event listener que contiene una función que invoca otra función para psarale parametros. 
document.getElementById('allTypeButton').addEventListener('click', function() { filterType(undefined); setFilterValue();});

document.getElementById('cleanFilterButton').addEventListener('click', showAll) /* BOTON DE LIMPIAR FILTRO */