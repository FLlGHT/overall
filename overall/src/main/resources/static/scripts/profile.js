

function changeRangeValue(slider) {
    let id = slider.id;
    let value = (slider.value - slider.min) / (slider.max - slider.min) * 100
    slider.style.background = 'linear-gradient(to right, #6b8dff 0%, #ff2a5f ' + value + '%, #fff ' + value + '%, #fff 100%)'
    document.getElementById('output' + id).textContent = slider.value;
}





