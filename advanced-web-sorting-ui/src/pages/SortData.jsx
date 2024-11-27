import { useState, useEffect } from "react";
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

const SortData = ({ user }) => {
  const [fileBody, setFilebody] = useState({
    sortData: "",
    algorithm: "",
  });
  const [data, setData] = useState([]);
  const [selectedAlgorithm, setSelectedAlgorithm] = useState("");
  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const { midText } = responsivenes();

  const handleChange = (e) => {
    const value = e.target.value;
    setFilebody({ ...fileBody, [e.target.name]: value });
  };

  const uploadFile = async (e) => {
    e.preventDefault();
    console.log(fileBody);
    try {
      setIsLoading(true);
      const { data } =
        (await WebSortAlgorithms.sortWebData(fileBody, user)) || {};
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

  useEffect(() => {
    (async () => {
      try {
        const { data } = await WebSortAlgorithms.getAllAlgorithms(user);
        setData(data);
      } catch (error) {}
    })();
  }, [user]);

  const handleSelectChange = (e) => {
    setSelectedAlgorithm(e.target.value);
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
            Sorting Time ⚡
          </Text>
          <Text fw={700} fz={20}>
            It looks like you are ready to sort some data, Load them in and let
            sort together ⚡
          </Text>
          <div className="mx-auto max-w-2xl font-bold text-xl">
            <div>{message ? message : ""}</div>
            <div className="my-5">
              <label>
                Digits only, and they should be separated by single spaces only
              </label>
              <Input
                onChange={(e) => handleChange(e)}
                placeholder="numbers to sort"
                name="sortData"
                value={fileBody?.sortData}
              />
            </div>
            <div>
              <select
                value={selectedAlgorithm}
                onChange={(e) => handleChange(e)}
                name="algorithm"
              >
                <option value="">Select an algorithm to use</option>
                {data.map((algorithm) => (
                  <option key={algorithm} value={algorithm}>
                    {algorithm}
                  </option>
                ))}
              </select>
            </div>
            <Flex justify={"center"} py={50}>
              <Button onClick={uploadFile} mr={10}>
                Sort numbers
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

export default SortData;
