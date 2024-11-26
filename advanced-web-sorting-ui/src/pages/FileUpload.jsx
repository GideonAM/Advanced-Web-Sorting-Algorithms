import { useState } from "react";
import WebSortAlgorithms from "../service/WebSortAlgorithms";
import {
  Button,
  Container,
  Flex,
  Image,
  Input,
  LoadingOverlay,
  Text,
  Textarea,
} from "@mantine/core";
import { Link } from "react-router-dom";
import { IconArrowBack } from "@tabler/icons-react";
import { notifications } from "@mantine/notifications";
import { responsivenes } from "../utils/responsivenes";

const FileUpload = ({ user }) => {
  const [fileBody, setFilebody] = useState({
    data: "",
  });
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const { midText } = responsivenes();

  const handleChange = (e) => {
    const value = e.target.value;
    setFilebody({ ...fileBody, [e.target.name]: value });
  };

  const uploadFile = async (e) => {
    e.preventDefault();

    try {
      setIsLoading(true);
      const { data } = (await WebSortAlgorithms.addData(fileBody, user)) || {};
      setMessage(data);
      setIsLoading(false);
      notifications?.show({
        title: "Success",
        message: data,
        color: "green",
      });
    } catch (error) {
      const { data } = error?.response || {};
      setIsLoading(false);
      notifications?.show({
        title: "Oh No!!!",
        message: data || "Something went wrong! 🧐🧐🧐🧐",
        color: "red",
      });
    }
  };

  return (
    <Container>
      <LoadingOverlay
        visible={isLoading}
        zIndex={1000}
        overlayProps={{ radius: "sm", blur: 2 }}
      />
      <form>
        <div className="m-5">
          <Text
            variant="gradient"
            gradient={{ from: "orange", to: "pink", deg: 90 }}
            fw={700}
            size={midText}
          >
            Hey there! Welcome aboard 🚢🚢🚢
          </Text>
          <Text fw={700} fz={20}>
            It looks like you are ready to add some dummy data, Load them in and
            let explore together🚀🚀🚀
          </Text>
          <div className="mx-auto max-w-2xl font-bold text-xl">
            <div>{message ? message : ""}</div>
            <div className="my-5">
              <label>Data content: </label>
              <Input
                onChange={(e) => handleChange(e)}
                placeholder="any data here"
                name="data"
                value={fileBody.data}
              />
            </div>
            <Flex justify={"center"}>
              <Button onClick={uploadFile} mr={10}>
                Add data
              </Button>
              <Link to={"/admin"}>
                <Button
                  variant="light"
                  leftSection={<IconArrowBack size={14} />}
                >
                  Back to Data
                </Button>
              </Link>
            </Flex>
          </div>
        </div>
      </form>
    </Container>
  );
};

export default FileUpload;
