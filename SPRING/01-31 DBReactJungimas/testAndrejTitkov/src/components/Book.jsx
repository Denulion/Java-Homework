import { putData } from "../helpers/put";
import Form from "./Form";
import { useState } from "react";

function Book({ book, setUpdate }) {
  const [formOpen, setFormOpen] = useState(false);
  const { title, author, category, price, cover, reserved } = book;
  const [isReserved, setIsReserved] = useState(reserved);

  const handleToggle = async () => {
    try {
      const updatedReservedState = !isReserved;

      console.log("Отправка:", { ...book, reserved: updatedReservedState });

      const updatedObject = await putData(book.id, { ...book, reserved: updatedReservedState });
  
      console.log("Ответ от сервера:", updatedObject);

      setIsReserved(updatedObject.reserved);
      setUpdate((prev) => prev + 1);
    } catch (error) {
      console.error('Error toggling reservation state:', error);
    }
  };

  return (
    <div className="border border-black rounded m-3 p-2 bg-slate-400">
      <img src={cover} alt="Book cover" className="w-2/4" />
      <div className="shadow shadow-slate-800 m-3 p-2 text-center">
        <h3 className="">{title}</h3>
        <p>{author}</p>
        <p>Category: {category}</p>
        <p>{price}</p>
      </div>
      <div>
        {isReserved ? (
          <button
            className="m-3 shadow-zinc-900 shadow p-2 bg-slate-400 text-black"
            onClick={handleToggle}
          >
            Reserved
          </button>
        ) : (
          <button
            className="m-3 shadow-zinc-900 shadow p-2 bg-slate-400 text-black"
            onClick={handleToggle}
          >
            Reserve
          </button>
        )}
        <button
          className="m-3 shadow-zinc-900 shadow p-2 bg-slate-400 text-black"
          onClick={() => setFormOpen(true)}
        >
          Edit information about the Book ✍️
        </button>
        {formOpen && (
          <Form book={book} setUpdate={setUpdate} setFormOpen={setFormOpen} />
        )}
      </div>
    </div>
  );
}

export default Book;
