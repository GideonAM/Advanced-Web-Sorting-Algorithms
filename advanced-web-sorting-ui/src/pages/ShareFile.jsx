import { useState } from "react";
import WebSortAlgorithms from "../service/WebSortAlgorithms";
import { Link, useParams } from "react-router-dom";
import { Button, Input } from "@mantine/core";
import { IconArrowBack } from "@tabler/icons-react";

const ShareFile = ({ user }) => {
  const { dataId } = useParams();
  const [fileBody, setFileBody] = useState({
    dataId,
    data: "",
  });

  const [message, setMessage] = useState("");

  const sendFile = (e) => {
    e.preventDefault();
    WebSortAlgorithms.updateData(fileBody, user)
      .then((response) => {
        alert(response.data);
      })
      .catch((error) => {
        let message = error.response.data;
        setMessage(message);
        setTimeout(() => setMessage(""), 5000);
      });
  };

  const handleChange = (e) => {
    const value = e.target.value;
    setFileBody({ ...fileBody, [e.target.name]: value });
  };

  return (
    <div className="p-11">
      <form className="mx-auto flex flex-col md:flex-row border-b shadow max-w-2xl">
        <div className="p-11">
          <div className="text-xl text-red-500">{message ? message : ""}</div>
          <div>
            <label className="font-semibold text-xl">
              Enter new text to replace old text:
            </label>
            <Input
              type="text"
              name="data"
              value={fileBody.data}
              className="w-full md:w-[30rem]"
              onChange={(e) => handleChange(e)}
            />
          </div>
          <div className="mt-2 flex flex-col md:flex-row">
            <Button mr={30} onClick={sendFile} className="mb-2 md:mb-0">
              Update data
            </Button>
            {user.isAdmin ? (
              <Link to={"/admin"}>
                <Button
                  variant="light"
                  leftSection={<IconArrowBack size={14} />}
                >
                  Back to Files
                </Button>
              </Link>
            ) : (
              <Link to={"/admin"}>
                <Button
                  variant="light"
                  leftSection={<IconArrowBack size={14} />}
                >
                  Back to Files
                </Button>
              </Link>
            )}
          </div>
        </div>
      </form>
    </div>
  );
};

export default ShareFile;
