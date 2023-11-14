import { films } from "../js/stock.js";
var homePopularFilms = document.querySelector('.most-popular-films');
var homeTopFilms = 10;
var e = 10;

while(e >= 1){
for (var i in films){
    
    if (films[i].popindex == homeTopFilms) {
        console.log(films[i].release);

        fetch = homePopularFilms.innerHTML;

        homePopularFilms.innerHTML = `
        <div id="popularFilmCard${i}" class="boxes">
        <div class="box-content">
        <p style=" ">${films[i].popindex}</p>
                <div class="box-content-son pmg-box-margin" onclick="showPopup(${i})">
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
        ` + fetch;
        var bgimg = document.getElementById(`popularFilmCard${i}`)
        bgimg.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
    }
       
}
            e--;
    homeTopFilms--;    
}