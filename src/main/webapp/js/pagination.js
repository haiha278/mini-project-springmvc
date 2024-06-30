var currentPage = document.getElementById('page').value;
var pageSize = document.getElementById('size').value;

function goToPage(pageNumber) {

    var url = '/employee-list?page=' + pageNumber + '&size=' + pageSize;
    window.location.href = url;
}

function updatePageSize() {
    pageSize = document.getElementById('page-size').value;
    var pageNumber = currentPage;
    var url = '/employee-list?page=' + pageNumber + '&size=' + pageSize;
    window.location.href = url;
}
