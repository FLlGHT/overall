let percentageValue = $('#overall-value').val();


jQuery.fn.getCircleLength = function () {
    console.log('gcl');
    let radius = this.attr('r');
    return 2 * Math.PI * radius;
};

function makePie(mold) {
    $(mold).html('<svg class="pie" viewBox="0 0 200 200"><circle cx="100" cy="100" r="88.936" class="pie-trail"/><circle ' +
        'cx="100" cy="100" r="88.936" class="pie-stuffing"/></svg><span class="counter"></span>');

    let stuffing = $(mold + ' .pie-stuffing'),
        lineWidth = parseInt(stuffing.css("stroke-width"), 10);
    let totalLength = $(stuffing).getCircleLength() + lineWidth;
    console.log(stuffing.get(0));
    $(mold + " span").data("current", 0);

    $(stuffing).css({
        'stroke-dashoffset': totalLength,
        'stroke-dasharray': totalLength + ' ' + totalLength
    })
}


function bakePie(mold, options) {
    let defaults = {
        percentage: $(mold).data("percentage"),
        duration: $(mold).data("baking-time"),
        counter: $(mold + " span").data("current"),
        frame: 100
    };
    $.extend(true, defaults, options);
    options = defaults;

    let stuffing = $(mold + ' .pie-stuffing'),
        lineWidth = parseInt(stuffing.css("stroke-width"), 10),
        totalLength = $(stuffing).getCircleLength() + lineWidth,
        counter = options.counter,
        toCount = options.percentage - counter,
        pieChunk = toCount / (options.duration / options.frame),
        almostDone = options.percentage - pieChunk,
        iterate = setTimeout(count, options.frame);

    function count() {
        counter += pieChunk;
        $(mold + " span").data("current", counter).text(Math.round(counter));

        if (counter < almostDone && pieChunk > 0) {
            iterate = setTimeout(count, options.frame);
        } else if (counter > almostDone && pieChunk < 0) {
            iterate = setTimeout(count, options.frame);
        } else {
            iterate = setTimeout(function () {
                $(mold + " span").data("current", options.percentage).text(options.percentage);
                clearTimeout(iterate);
            }, options.frame);
        }
    }

    $(stuffing).animate({
        'stroke-dashoffset': totalLength * (100 - options.percentage) / 100
    }, options.duration);
}


makePie('#pie-mold');

// setTimeout(function () {
//     bakePie('#pie-mold');
// }, 2000);


setTimeout(function () {
    bakePie('#pie-mold', {percentage: percentageValue});
}, 200);

//
// setTimeout(function () {
//     bakePie('#pie-mold', {percentage: 100});
// }, 10000);