let visibleCardElements = null;

visibleCardElements = getVisibleCardsElementsValue();

function getVisibleCardsElementsValue() {

    if($(this).width() > 1199)
        visibleCardElements = 4;
    else if ($(this).width() > 940)
        visibleCardElements = 3;
    else
        visibleCardElements = 2;

    return visibleCardElements;
}

let gapBetweenTwoCardElements = ($("#workoutView").width() -
    ($(".card").width() * visibleCardElements)) / (visibleCardElements - 1);

let scrollSpeed = $(".card").width() + gapBetweenTwoCardElements; //fix scrollspeed for 3 or less cards!


////

alert($(".col-10").width());
////







$(window).resize(function() {

    visibleCardElements = getVisibleCardsElementsValue();

    scrollSpeed = $(".card").width() + gapBetweenTwoCardElements;

});

$("#arrow-right").click(function () {

    document.getElementById('workoutView').scrollLeft += scrollSpeed;

});

$("#arrow-left").click(function () {

    document.getElementById('workoutView').scrollLeft -= scrollSpeed;

});





// Code for horizontal Scroll for Workouts

let current = 0;

let ScrollX_pixelPer = 40;

$("#workoutView").on("mousewheel", function(e) {

    e.preventDefault();

    let delta = ScrollX_pixelPer * (parseInt(e.originalEvent.deltaY) / 33);

    current += delta;

    $(this).scrollLeft(current);

});



