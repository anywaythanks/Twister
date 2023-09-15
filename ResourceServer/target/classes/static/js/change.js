function changeValues(val, ...ids) {
    const num = parseInt(val, 10);
    ids.forEach(id => {
        const element = document.getElementById(id);
        const max = parseInt(element.getAttribute("data-max"), 10);
        const min = parseInt(element.getAttribute("data-min"), 10);
        const multiplicator = parseInt(element.getAttribute("data-multiplicator"), 10);
        let result = isNaN(num) ? 0 : num;
        result = isNaN(max) ? result : Math.min(max, result);
        result = isNaN(min) ? result : Math.max(min, result);
        result *= isNaN(multiplicator) ? 1 : multiplicator;
        element.innerHTML = result.toLocaleString();
        element.value = result;
    });
}