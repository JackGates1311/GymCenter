let visibleCardElements = null;
let scrollSpeed = null;
let cardContainerSize = null;
let cardWidth = null;
let gapBetweenTwoCardElements = null;

let current = 0;

visibleCardElements = getVisibleCardsElementsValue();
scrollSpeed = getScrollSpeedValue();

function getVisibleCardsElementsValue() {

    if($(this).width() > 1199)
        visibleCardElements = 4;
    else if ($(this).width() > 991)
        visibleCardElements = 3;
    else
        visibleCardElements = 2;

    return visibleCardElements;
}

function getScrollSpeedValue() {

    cardContainerSize = $("#workoutViewGuest").width();

    cardWidth = $(".card").width();

    gapBetweenTwoCardElements = (cardContainerSize - (cardWidth * visibleCardElements)) / (visibleCardElements);

    scrollSpeed = cardWidth + gapBetweenTwoCardElements;

    return scrollSpeed;
}

$(window).resize(function() {

    visibleCardElements = getVisibleCardsElementsValue();

    scrollSpeed = getScrollSpeedValue();

});

$("#arrow-right").click(function () {

    document.getElementById('workoutViewGuest').scrollLeft += scrollSpeed;

});

$("#arrow-left").click(function () {

    document.getElementById('workoutViewGuest').scrollLeft -= scrollSpeed;

});

$("#workoutViewGuest").on("mousewheel", function(e) {

    e.preventDefault();

    let delta = parseInt(e.originalEvent.deltaY);

    if(delta > 0)
        current += scrollSpeed;
    else
        current -= scrollSpeed;

    $(this).scrollLeft(current);
});



