import Book from "./Book";


function BookList({ books, setUpdate }) {
  return (
    <>
      <div className="grid grid-rows-4 grid-flow-col gap-2">
        {books.map((book) => (
          <Book book={book} key={book.id} setUpdate={setUpdate} />
        ))}
      </div>
    </>
  );
}

export default BookList;
