function initializeDataTable(tableId, copyButtonId, exportButtonId, numberOfColumns) {
    // Quitar preloader si la tabla no se carga en 5 segundos
    var preloaderTimeout = setTimeout(function() {
        $('body').addClass('loaded');
        $('#preloader').remove();
    }, 5000);

    $(document).ready(function() {
        var table = $(`#${tableId}`).DataTable({
            dom: 'Bfrtip',
            paging: true, // Habilitar la paginación
            pageLength: 10, // Cantidad de registros por página
            language: {
                info: "Mostrando _START_ a _END_ de _TOTAL_ filas",
            },
            buttons: [
                {
                    extend: 'excelHtml5',
                    titleAttr: 'Exportar a Excel',
                    className: 'd-none', // Oculta el botón en la interfaz
                    // Incluir solo las primeras n columnas
                    exportOptions: {
                        columns: Array.from({length: numberOfColumns}, (v, k) => k)
                    }
                },
                {
                    extend: 'copyHtml5',
                    titleAttr: '',
                    className: 'd-none', // Oculta el botón en la interfaz
                    exportOptions: {
                        columns: Array.from({length: numberOfColumns}, (v, k) => k)
                    }
                }
            ],
            drawCallback: function() {
                // Estilos botones de paginación
                $('.dataTables_paginate .paginate_button').addClass('btn btn-sm btn-outline-secondary mx-1');
                $('.dataTables_paginate .paginate_button.current').removeClass('btn-outline-secondary').addClass('btn-secondary');
            },
            // Quitar preloader al cargar la tabla
            initComplete: function () {
                clearTimeout(preloaderTimeout);
                $('body').addClass('loaded');
                $('#preloader').remove();
            }
        });

        function tableToJson() {
            var data = table.rows().data().toArray();
            var headers = table.columns().header().toArray().map(function (header) {
                return $(header).text();
            });

            return data.map(function (row) {
                var obj = {};
                row.slice(0, numberOfColumns).forEach(function (cell, i) {
                    obj[headers[i]] = cell;
                });
                return obj;
            });
        }

        // Copiar JSON al portapapeles
        $('#' + copyButtonId).on('click', function () {
            var json = JSON.stringify(tableToJson(), null, 2);
            var tempInput = $('<textarea>');
            $('body').append(tempInput);
            tempInput.val(json).select();
            document.execCommand('copy');
            tempInput.remove();

            alert('JSON copiado al portapapeles');
        });

        // Estilos de la barra de búsqueda
        $(`.dataTables_filter`).addClass('d-flex justify-content-end m-1');
        $(`.dataTables_filter input`).addClass('form-control me-2');
        $(`.dataTables_filter label`).addClass('flex-fill');
        $(`.dataTables_filter label`).contents().filter(function() {
            return this.nodeType === 3; // Nodo de texto
        }).remove();
        $(`.dataTables_filter input`).attr("placeholder", 'Buscar');

        // Estilos de paginación
        $(`.dataTables_paginate`).addClass('d-flex justify-content-center');
        $(`.dataTables_info`).addClass('text-center text-muted m-2');

        var excelButton = table.buttons('.buttons-excel');
        var copyButton = table.buttons('.buttons-copy');

        // Evento de clic en el botón de exportar a Excel
        $('#' + exportButtonId).on('click', function() {
            excelButton.trigger();
        });
    });
}
