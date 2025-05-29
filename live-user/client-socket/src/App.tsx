import { useApp } from "./hooks/useApp";

const App = () => {

  const {
    data,
  } = useApp();

  return (
    <div>
      <h1>Real-time Updates</h1>
      <p>{data}</p>
    </div>
  );

};

export default App;
