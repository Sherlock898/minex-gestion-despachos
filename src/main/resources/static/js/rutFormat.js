// Formato del rut, maximo de caracteres (9), guion automatico y solo numeros y K
document.getElementById("rut").addEventListener("input", function() {
    let rut = this.value.replace(/[^0-9Kk]/g, ''); // Remueve caracteres invalidos

    // Insertar guion
    if (rut.length > 7) {
        rut = rut.slice(0, -1) + '-' + rut.slice(-1);
    }

    // Limitar cantidad de caracteres
    let maxLength = 9;
    if (rut.replace('-', '').length > maxLength) {
        rut = rut.slice(0, maxLength);
        if (rut.length > 7) {
            rut = rut.slice(0, -1) + '-' + rut.slice(-1);
        }
    }

    this.value = rut;
});