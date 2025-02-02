import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { postdata } from "../helpers/post";
import { putData } from "../helpers/put";
import { getCategory } from "../helpers/get";

function Form({ setUpdate, book, setFormOpen }) {
  const [error, setError] = useState("");
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const categoriesData = await getCategory();
        const uniqueCategories = Array.from(new Set(categoriesData));
        setCategories(uniqueCategories);
      } catch (error) {
        console.error("error while loading categories", error);
      }
    };
    fetchCategories();
  }, []);

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    setValue,
  } = useForm({
    mode: "onBlur",
    reValidateMode: "onChange",
  });

  useEffect(() => {
    if (book) {
      const { title, author, category, price, cover } = book;
      setValue("title", title);
      setValue("author", author);
      setValue("category", category);
      setValue("price", price);
      setValue("cover", cover);
    }
  }, [book, setValue]);

  const formSubmitHandler = async (data) => {
    try {
      if (book) {
        await putData(book.id, {...book, ...data});
        setFormOpen(false);
      } else {
        await postdata({ ...data, reserved: false });
      }
      setUpdate((prev) => prev + 1);
      reset();
    } catch (error) {
      setError(error.message);
    }
  };
  return (
    <>
      <div>
        <form
          onSubmit={handleSubmit(formSubmitHandler)}
          className="text-center p=3"
        >
          <div className="p-3">
            <label htmlFor="title" className="pr-3">Title</label>
            <input
              type="text"
              id="title"
              placeholder="Enter book title"
              {...register("title", {
                required: "Book shoud have a title",
                minLength: 3,
                maxLength: 100,
                pattern: {
                  value: /^[a-zA-Z.]+(?:\s+[a-zA-Z.]+)*$/g,
                  message: "Title has disallowed symbols",
                },
              })}
            />
            <p className="text-red-600">{errors.title?.message}</p>
          </div>
          <div className="p-3">
            <label htmlFor="author" className="pr-3">Author</label>
            <input
              type="text"
              id="author"
              placeholder="Enter book's author full name"
              {...register("author", {
                required: "Author's full name is required",
                pattern: {
                  value: /^[a-zA-Z]+(?: [a-zA-Z]+)*$/g,
                  message: "Author's name has disallowed symbols",
                },
              })}
            />
            <p className="text-red-600">{errors.author?.message}</p>
          </div>
          <div className="p-3">
            <label htmlFor="category" className="pr-3">Category</label>
            <select
              id="category"
              {...register("category", { required: "Category is required" })}
            >
              <option>Select a category</option>
              {categories.map((category, index) => (
                <option value={category} key={index}>
                  {category}
                </option>
              ))}
            </select>
            <p className="text-red-600">{errors.category?.message}</p>
          </div>
          <div className="p-3">
            <label htmlFor="price" className="pr-3">Price</label>
            <input
              type="text"
              id="price"
              placeholder="Enter book's price"
              {...register("price", {
                required: "Price is required",
                min: {
                  value: 0.1,
                  message: "Your price is too low",
                },
                pattern: {
                  value: /^\d+([,\.]\d+)*$/,
                  message: "Your number has dissalowed symbols",
                },
              })}
            />
            <p className="text-red-600">{errors.price?.message}</p>
          </div>
          <div className="p-3">
            <label htmlFor="cover" className="pr-3">Book cover</label>
            <input
              type="text"
              id="cover"
              {...register("cover", {
                required: "Book should have a cover",
                pattern: {
                  value:
                    /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/,
                  message: "You have disallowed symbols in your URL",
                },
              })}
            />
            <p className="text-red-600">{errors.cover?.message}</p>
          </div>
          <input type="submit" value="Submit" className="bg-yellow-300 p-3 rounded border border-black shadow-slate-600 shadow" />
        </form>
        {error && <p className="text-red-600">{error}</p>}
      </div>
    </>
  );
}

export default Form;
