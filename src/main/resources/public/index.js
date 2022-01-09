window.onload = function() {
    const apiRootUrl = './api/books';

    let bookEntries = [];

    const form = {
        id: document.getElementById('book-form-input-id'),
        title: document.getElementById('book-form-input-title'),
        author: document.getElementById('book-form-input-author'),
        description: document.getElementById('book-form-input-description'),
    };

    const formButtons = {
        saveNewButton: document.getElementById('book-form-button-save-new'),
        saveButton: document.getElementById('book-form-button-save'),
        deleteButton: document.getElementById('book-form-button-delete')
    };

    formButtons.saveNewButton.addEventListener('click', onSaveNewClicked);
    formButtons.saveButton.addEventListener('click', onSaveClicked);
    formButtons.deleteButton.addEventListener('click', onDeleteClicked);

    const bookList = document.getElementById('book-list');

    bookList.addEventListener('change', onSelectionChanged);

    reloadBooks();

    // API calls

    function getBooks() {
        return fetch(apiRootUrl, {
            headers: {
                "Accept": 'application/json'
            }}).then(res => res.status === 200 ? res.json() : Promise.reject(new Error("Loading books failed")));
    }

    function postBook(book) {
        return fetch(apiRootUrl, {
            method: "POST",
            body: JSON.stringify(book),
            headers: {
                "Accept": 'application/json',
                "Content-type": 'application/json'
            }}).then(res => res.status === 200 ? res.json() : Promise.reject(new Error("Saving the book failed")));
    }

    function putBook(book) {
        return fetch(`${apiRootUrl}/${book.id}`, {
            method: "PUT",
            body: JSON.stringify(book),
            headers: {
                "Accept": 'application/json',
                "Content-type": 'application/json'
            }}).then(res => res.status === 200 ? res.json() : Promise.reject(new Error("Saving the book failed")));
    }

    function getBookById(id) {
        return fetch(`${apiRootUrl}/${id}`, {
            headers: {
                "Accept": 'application/json',
                "Content-type": 'application/json'
            }}).then(res => res.status === 200 ? res.json() : Promise.reject(new Error("Loading the book failed")));
    }

    function deleteBookById(id) {
        return fetch(`${apiRootUrl}/${id}`, {
            method: "DELETE"
        }).then(res => {
            if (res.status >= 300) {
                return Promise.reject(new Error("Deleting the book failed"))
            }
        });
    }

    // Event handlers

    function onSelectionChanged(event) {
        event.preventDefault();
        const id = parseInt(event.target.value);

        if (isNaN(id) || id === -1) {
            clearFormValues();
            toggleFormButtons(true);
            bookList.value = null;
            form.title.focus()
        } else {
            const selected = bookEntries.find(book => book.id === id);
            populateBookForm(selected);
            toggleFormButtons(false);
        }
    }

    function onSaveNewClicked(event) {
        event.preventDefault();

        const book = getItemFromFormValues();

        postBook(book).then(saved => {
            bookEntries = bookEntries.concat(saved);
            bookList.insertBefore(createBookListOption(saved), bookList.lastElementChild);
            clearBookListSelection();
        });
    }

    function onSaveClicked(event) {
        event.preventDefault();

        const book = getItemFromFormValues();

        putBook(book).then(updated => {
            const index = bookEntries.findIndex(book => book.id === updated.id);
            bookEntries.splice(index, 1, updated);

            const nextElement = removeBookListOption(updated);
            bookList.insertBefore(createBookListOption(updated), nextElement);
            clearBookListSelection();
        });
    }

    function onDeleteClicked(event) {
        event.preventDefault();

        const deleted = getItemFromFormValues();

        deleteBookById(deleted.id).then(() => {
            const index = bookEntries.findIndex(book => book.id === deleted.id);
            bookEntries.splice(index, 1);
            removeBookListOption(deleted);
            clearBookListSelection();
        });
    }

    // Form helpers

    function populateBookForm(book) {
        Object.entries(book).forEach(([key, value]) => {
            form[key].value = value;
        });
    }

    function clearFormValues() {
        Object.values(form).forEach(val => val.value = "");
    }

    function getItemFromFormValues() {
        return Object.fromEntries(
            Object.entries(form)
                .map(([inputName, input]) =>
                    [inputName, inputName === 'id' ? parseInt(input.value) : input.value]
                ));
    }

    function toggleFormButtons(addNew) {
        if (addNew) {
            formButtons.deleteButton.setAttribute('disabled', 'disabled');
            formButtons.saveButton.setAttribute('disabled', 'disabled');
            formButtons.saveNewButton.removeAttribute('disabled');
        } else {
            formButtons.deleteButton.removeAttribute('disabled');
            formButtons.saveButton.removeAttribute('disabled');
            formButtons.saveNewButton.setAttribute('disabled', 'disabled');
        }
    }

    // Book list helpers

    function populateBookList(books) {
        const addNewOption = bookList.lastElementChild;

        while (addNewOption.previousElementSibling) {
            bookList.removeChild(addNewOption.previousElementSibling);
        }

        books.forEach(book => bookList.insertBefore(createBookListOption(book), addNewOption));
    }

    function createBookListOption(book) {
        const optionElement = document.createElement('option');

        optionElement.value = book.id;
        optionElement.innerText = `${book.author}: ${book.title}`;

        return optionElement;
    }

    function removeBookListOption(book) {
        const bookOption = Array.from(bookList.children).find(node => parseInt(node.getAttribute('value')) === book.id);
        const nextElement = bookOption.nextElementSibling;

        bookList.removeChild(bookOption);

        return nextElement;
    }

    function clearBookListSelection() {
        bookList.value = null;
        bookList.dispatchEvent(new Event('change'));
    }

    function reloadBooks() {
        getBooks().then(books => {
            bookEntries = bookEntries.concat(...books);
            populateBookList(bookEntries);
        });
    }
};