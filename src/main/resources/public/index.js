const apiRootUrl = './api/books/'

// API calls

function getBooks() {
    return fetch(apiRootUrl, {
        headers: {
            "Accept": 'application/json'
        }}).then(res => res.json())
}

function postBook(book) {
    return fetch(apiRootUrl, {
        method: "POST",
        body: JSON.stringify(book),
        headers: {
            "Accept": 'application/json',
            "Content-type": 'application/json'
        }}).then(res => res.json())
}

function putBook(book) {
    return fetch(apiRootUrl, {
        method: "PUT",
        body: JSON.stringify(book),
        headers: {
            "Accept": 'application/json',
            "Content-type": 'application/json'
        }}).then(res => res.json())
}

function getBookById(id) {
    return fetch(apiRootUrl, {
        headers: {
            "Accept": 'application/json',
            "Content-type": 'application/json'
        }}).then(res => res.json())
}

function deleteBookById(id) {
    return fetch(apiRootUrl, {
        method: "DELETE"
    }).then(res => res.json())
}

// Event handlers

function onSelectionChanged(event) {
    event.preventDefault()
    console.log(event)
}

function onSaveNewClicked(event) {
    event.preventDefault()
    console.log(event)
}

function onSaveClicked(event) {
    event.preventDefault()
    console.log(event)
}

function onDeleteClicked(event) {
    event.preventDefault()
    console.log(event)
}

// Form helpers

function clearFormValues(form) {
    Object.values(form).forEach(val => val.value = "")
}

// Book list helpers

function populateBookList(bookList, books) {
    books.forEach(book => addBookItem(bookList, book))
}

function addBookItem(bookList, book) {
    const optionElement = document.createElement('option')
    optionElement.value = book.id
    optionElement.innerText = `${book.author}: ${book.title}`

    const addNewOption = bookList.lastElementChild
    bookList.insertBefore(optionElement, addNewOption)
}

window.onload = function() {
    const form = {
        id: document.getElementById('book-form-input-id'),
        title: document.getElementById('book-form-input-title'),
        author: document.getElementById('book-form-input-author'),
        description: document.getElementById('book-form-input-description'),
    }

    const saveNewButton = document.getElementById('book-form-button-save-new')
    const saveButton = document.getElementById('book-form-button-save')
    const deleteButton = document.getElementById('book-form-button-delete')

    const bookList = document.getElementById('book-list')

    saveNewButton.addEventListener('click', onSaveNewClicked)
    saveButton.addEventListener('click', onSaveClicked)
    deleteButton.addEventListener('click', onDeleteClicked)
    bookList.addEventListener('change', onSelectionChanged)

    getBooks().then(books => populateBookList(bookList, books))
}