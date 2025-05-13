document.addEventListener('DOMContentLoaded', function() {
    
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', function(e) {
            if (!confirm('Apakah Anda yakin ingin menghapus data ini?')) {
                e.preventDefault();
            }
        });
    });

    
    document.querySelector('.btn-print')?.addEventListener('click', function() {
        window.print();
    });

   
    const searchInput = document.getElementById('searchInput');
    const searchForm = document.getElementById('searchForm');
    
    if (searchInput && searchForm) {
        let timeoutId;
        
        searchInput.addEventListener('input', function() {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(() => {
                searchForm.submit();
            }, 500); 
        });
    }

   
    const currentPath = window.location.pathname;
    document.querySelectorAll('.nav-link').forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        }
    });

   
    document.querySelector('form')?.addEventListener('submit', function(e) {
        const platNomor = document.querySelector('[name="platNomor"]').value;
        const platPattern = /^[A-Z]{1,2}\s?\d{1,4}\s?[A-Z]{1,3}$/;
        
        if (!platPattern.test(platNomor)) {
            e.preventDefault();
            alert('Format plat nomor tidak valid! Contoh: B 1234 CD');
        }
    });

    function sortTable(column) {
        const table = document.querySelector('table');
        const rows = Array.from(table.querySelectorAll('tbody tr'));
        const index = column === 'nama' ? 1 : 4; // 1 for nama, 4 for waktu
        
        rows.sort((a, b) => {
            const aText = a.cells[index].textContent;
            const bText = b.cells[index].textContent;
            return aText.localeCompare(bText);
        });
        
        rows.forEach(row => table.querySelector('tbody').appendChild(row));
    }
});