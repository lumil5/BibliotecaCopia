/**
 * Lógica Javascript para Notificaciones Toast
 */

/**
 * Cierra un toast con animación suave de desvanecimiento
 * @param {string} id - El ID del elemento Toast a cerrar
 */
function cerrarToast(id) {
    const el = document.getElementById(id);
    if (el) {
        el.style.opacity = '0';
        el.style.transform = 'translateY(-12px)';
        setTimeout(() => {
            if (el && el.parentNode) {
                el.remove();
            }
        }, 300);
    }
}
