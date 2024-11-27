import { useEffect, useState } from "react";
import WebSortAlgorithms from "../service/WebSortAlgorithms";
import {
  Box,
  Button,
  Container,
  Group,
  Paper,
  Skeleton,
  Table,
  Text,
  Modal,
} from "@mantine/core";
import { IconCloudUpload, IconEdit, IconTrash } from "@tabler/icons-react";
import { Link, useNavigate } from "react-router-dom";

const FeedAdmin = ({ user, dispatchUser }) => {
  const navigate = useNavigate();
  const [noTransitionOpened, setNoTransitionOpened] = useState(false);

  const [files, setFiles] = useState([]);
  const [search, setSearch] = useState({
    name: "",
  });
  const [message, setMessage] = useState("");
  const [isLoading, setIsLoading] = useState("");
  const [fileName, setFileName] = useState("");

  const deleteFile = async (dataId) => {
    try {
      setIsLoading(true);
      const { data } = await WebSortAlgorithms.deleteDataById(dataId, user);
      if (files) {
        setFiles((prevFiles) => {
          setMessage(data);
          setIsLoading(false);
          setNoTransitionOpened(false);
          return prevFiles.filter((data) => data.id != dataId);
        });
        setTimeout(() => setMessage(""), 3000);
      }
    } catch (error) {
      setIsLoading(false);
    }
  };

  const handleDeleteFile = async (file) => {
    setFileName(file);
    setNoTransitionOpened(true);
  };

  const logout = async (e) => {
    e.preventDefault();
    WebSortAlgorithms.logout(user)
      .then((response) => {
        dispatchUser({ type: "clear-session", payload: response.data });
        alert(response.data);
        navigate("/login");
      })
      .catch((error) => {
        alert(error.response.data);
      });
  };

  useEffect(() => {
    (async () => {
      setIsLoading(true);
      try {
        const { data } = await WebSortAlgorithms.getAllData(user);
        setFiles(data);
        setIsLoading(false);
      } catch (error) {
        setIsLoading(false);
      }
    })();
  }, [user]);

  const rows = files.map((row) => {
    return (
      <Table.Tr key={row.id}>
        <Table.Td>{row.data}</Table.Td>
        <Table.Td>
          <Group justify="space-evenly">
            <span style={{ display: "flex" }}>
              <Text
                style={{ cursor: "pointer" }}
                onClick={() => handleDeleteFile(row)}
                fz="xs"
                c="red"
                fw={700}
              >
                Delete
              </Text>
              <IconTrash size={17} style={{ marginLeft: 2 }} color="red" />
            </span>
            <span style={{ display: "flex" }}>
              <Link to={`/share-file/${row.id}`}>
                <Text style={{ cursor: "pointer" }} fz="xs" c="red" fw={700}>
                  Update
                </Text>
              </Link>
              <IconEdit size={17} style={{ marginLeft: 2 }} color="red" />
            </span>
          </Group>
        </Table.Td>
      </Table.Tr>
    );
  });

  return (
    <>
      <Modal
        opened={noTransitionOpened}
        onClose={() => setNoTransitionOpened(false)}
        title={
          <Text fw={700}>
            Are you sure You want to delete{" "}
            <span style={{ fontSize: 15, color: "red" }}>
              {fileName?.data}{" "}
            </span>
          </Text>
        }
        transitionProps={{
          transition: "fade",
          duration: 600,
          timingFunction: "linear",
        }}
      >
        <Button
          bg={"red"}
          loading={isLoading}
          onClick={() => deleteFile(fileName?.id)}
          style={{ width: "5rem" }}
          c={"white"}
          mr={"2rem"}
        >
          {" "}
          Yes
        </Button>
        <Button c={"white"} onClick={() => setNoTransitionOpened(false)}>
          {" "}
          No
        </Button>
      </Modal>
      <div>
        <div className="container mx-auto p-3">
          <Container>
            <Paper
              elevation={1}
              style={{ display: "flex", justifyContent: "space-between" }}
            >
              <Link to={"/upload-file"}>
                <Button rightSection={<IconCloudUpload />}>
                  {" "}
                  Add some data
                </Button>
              </Link>
              <Link to={"/sort-data"}>
                <Button> Let's sort some data, shall we ⚡?</Button>
              </Link>

              <Box>
                <Button onClick={logout}>Logout</Button>
              </Box>
            </Paper>
          </Container>
          <div className="shadow border-b-4">
            <div className="text-red-500 font-medium text-xl">
              {message ? message : ""}
            </div>
          </div>
        </div>
      </div>

      {isLoading ? (
        <Container>
          <Skeleton height={40} mt={10} />
          <Skeleton height={40} mt={10} />
          <Skeleton height={40} mt={10} />
          <Skeleton height={40} mt={10} />
        </Container>
      ) : (
        <Container size={"lg"} mt={20}>
          {files?.length > 0 ? (
            <Table.ScrollContainer minWidth={800}>
              <Table verticalSpacing="xs">
                <Table.Thead>
                  <Table.Tr>
                    <Table.Th>Content</Table.Th>
                    <Table.Th>Actions</Table.Th>
                  </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
              </Table>
            </Table.ScrollContainer>
          ) : (
            <Container>
              <Text fw={700} fs={"10rem"} style={{ textAlign: "center" }}>
                Ops!!!!!! No data found
              </Text>
            </Container>
          )}
        </Container>
      )}
    </>
  );
};

export default FeedAdmin;
