import { films } from "../js/stock.js";
import { series } from "../js/stock.js";
import { documentals } from "../js/stock.js";

var homePopularSeries = document.querySelector('.most-popular-series');
var homeTopSeries = 10;
var homePopularFilms = document.querySelector('.most-popular-films');
var homeTopFilms = 10;
var contador = 10;
var homeKeepWatching = document.querySelector('.keep-watching');
var muchKeepWatchingFilms = 4;
var muchKeepWatchingSeries = 4;
var muchKeepWatchingDocumentals = 4;
var homeMightLikeSeries = document.querySelector('.might-like-series');
var homeMightLikeFilms = document.querySelector('.might-like-films');
var homeMightLikeDocumentals = document.querySelector('.might-like-documentals');
var muchMightLikeSeries = 13;
var muchMightLikeFilms = 13;
var muchMightLikeDocumentals = 13;

while(contador >= 1){
for(var i in series){
    if (series[i].popindex == homeTopSeries) {
        console.log(series[i].release);

        fetch = homePopularSeries.innerHTML;

        homePopularSeries.innerHTML = `
        <div id="popularSeriesCard${i}" class="pmg-boxes-top resize-top">
        <div class="box-content">
                <div class="pmg-box-content-son pmg-box-margin" onclick="showPopup(${i})">
                    <h5 class="titulopelicula">${series[i].title}</h5>
                    <p class="duracion">
                    ${series[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${series[i].release}
                    </p> 
                </div>
            <div class="pmg-top">
                <h3>${series[i].popindex}</h3>
            </div> 
            </div>
        </div>
        ` + fetch;

        var bgimgseries = document.getElementById(`popularSeriesCard${i}`)
        bgimgseries.style.backgroundImage = `url('img/170x240/series/${series[i].poster}.png')`;
    }
}
for (var i in films){
    
    if (films[i].popindex == homeTopFilms) {
        console.log(films[i].release);

        fetch = homePopularFilms.innerHTML;

        homePopularFilms.innerHTML = `
        <div id="popularFilmCard${i}" class="pmg-boxes-top resize-top">
        <div class="box-content">
                <div class="pmg-box-content-son pmg-box-margin" onclick="showPopup(${i})">
                    <h5 class="titulopelicula">${films[i].title}</h5>
                    <p class="duracion">
                    ${films[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${films[i].release}
                    </p> 
                </div> 
            <div class="pmg-top">
                <h3>${films[i].popindex}</h3>
            </div> 
            </div>
        </div>
        ` + fetch;

        var bgimgfilms = document.getElementById(`popularFilmCard${i}`)
        bgimgfilms.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
    }
       
    }
        contador--;
    homeTopFilms--;
    homeTopSeries--;    
}


for(var i in series){
        if(muchKeepWatchingSeries > 0){
            
            fetch = homeKeepWatching.innerHTML;

            homeKeepWatching.innerHTML = `
            <div id="popularSeriesCard${i}" class="pmg-boxes-watching resize">
            <div class="box-content">
                    <div class="pmg-box-content-son-watching" onclick="showPopup(${i})">
                        <h6 style="font-size: 15px;" class="titulopelicula">
                        ${series[i].title}
                        </h6>
                        <p class="duracion">
                        ${series[i].duration}
                        </p>
                        <p class="fechasalida">
                        ${series[i].release}
                        </p> 
                    </div> 
                </div>
            </div>
            ` + fetch;

            var bgimgseries = document.getElementById(`popularSeriesCard${i}`)
            bgimgseries.style.backgroundImage = `url('img/170x240/series/${series[i].poster}.png')`;
        }
        muchKeepWatchingSeries--;
    }
    for(var i in films){
        if(muchKeepWatchingFilms > 0){
            console.log(films[i].popindex);
            
            fetch = homeKeepWatching.innerHTML;

            homeKeepWatching.innerHTML = `
            <div id="popularFilmsCard${i}" class="pmg-boxes-watching resize">
            <div class="box-content">
                    <div class="pmg-box-content-son-watching" onclick="showPopup(${i})">
                        <h6 style="font-size: 15px;" class="titulopelicula">
                        ${films[i].title}
                        </h6>
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

            var bgimgfilms = document.getElementById(`popularFilmsCard${i}`)
            bgimgfilms.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
        }
        muchKeepWatchingFilms--;
    }
    for(var i in documentals){
        if(muchKeepWatchingDocumentals > 0){
            console.log(documentals[i].popindex);
            
            fetch = homeKeepWatching.innerHTML;

            homeKeepWatching.innerHTML = `
            <div id="popularDocumentalsCard${i}" class="pmg-boxes-watching resize">
            <div class="box-content">
                    <div class="pmg-box-content-son-watching" onclick="showPopup(${i})">
                        <h6 style="font-size: 15px;" class="titulopelicula">
                        ${documentals[i].title}
                        </h6>
                        <p class="duracion">
                        ${documentals[i].duration}
                        </p>
                        <p class="fechasalida">
                        ${documentals[i].release}
                        </p> 
                    </div> 
                </div>
            </div>
            ` + fetch;

            var bgimgdocumentals = document.getElementById(`popularDocumentalsCard${i}`)
            bgimgdocumentals.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;
            
        }
        muchKeepWatchingDocumentals--;
    }

for(var i in series){
        if(muchMightLikeSeries > 0){
            
            fetch = homeMightLikeSeries.innerHTML;

            homeMightLikeSeries.innerHTML = `
            <div id="popularSeriesCard${i}" class="pmg-boxes-might-like resize">
            <div class="box-content">
                    <div class="pmg-box-content-son-might-like" onclick="showPopup(${i})">
                        <h6 style="font-size: 15px;" class="titulopelicula">
                        ${series[i].title}
                        </h6>
                        <p class="duracion">
                        ${series[i].duration}
                        </p>
                        <p class="fechasalida">
                        ${series[i].release}
                        </p> 
                    </div> 
                </div>
            </div>
            ` + fetch;

            var bgimgseries = document.getElementById(`popularSeriesCard${i}`)
            bgimgseries.style.backgroundImage = `url('img/170x240/series/${series[i].poster}.png')`;
        }
        muchMightLikeSeries--;
}
for(var i in films){
    if( muchMightLikeFilms > 0){
        console.log(films[i].popindex);
        
        fetch = homeMightLikeFilms.innerHTML;

        homeMightLikeFilms.innerHTML = `
        <div id="popularFilmsCard${i}" class="pmg-boxes-might-like resize">
        <div class="box-content">
                <div class="pmg-box-content-son-might-like" onclick="showPopup(${i})">
                    <h6 style="font-size: 15px;" class="titulopelicula">
                    ${films[i].title}
                    </h6>
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

        var bgimgfilms = document.getElementById(`popularFilmsCard${i}`)
        bgimgfilms.style.backgroundImage = `url('img/170x240/films/${films[i].poster}.png')`;
    }
    muchMightLikeFilms--;
}
for(var i in documentals){
    if(muchMightLikeDocumentals > 0){
        console.log(documentals[i].popindex);
        
        fetch = homeMightLikeDocumentals.innerHTML;

        homeMightLikeDocumentals.innerHTML = `
        <div id="popularDocumentalsCard${i}" class="pmg-boxes-might-like resize">
        <div class="box-content">
                <div class="pmg-box-content-son-might-like" onclick="showPopup(${i})">
                    <h6 style="font-size: 15px;" class="titulopelicula">
                    ${documentals[i].title}
                    </h6>
                    <p class="duracion">
                    ${documentals[i].duration}
                    </p>
                    <p class="fechasalida">
                    ${documentals[i].release}
                    </p> 
                </div> 
            </div>
        </div>
        ` + fetch;

        var bgimgdocumentals = document.getElementById(`popularDocumentalsCard${i}`)
        bgimgdocumentals.style.backgroundImage = `url('img/170x240/documentals/${documentals[i].poster}.png')`;
        
    }
    muchMightLikeDocumentals--;
}