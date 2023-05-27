import { v4 as uuidv4 } from "uuid";
import Card from "../reusables/Card";
import Carousel from "../reusables/Carousel";


function World() {
  let cards = [
    {
      key: uuidv4(),
      content: (
        <Card imagen="https://www.wallpaperup.com/uploads/wallpapers/2014/11/09/511460/ed3730a5a6e1ecda1ed3729b8348cb54-700.jpg" />
      )
    },
    {
      key: uuidv4(),
      content: (
        <Card imagen="https://i.pinimg.com/originals/d1/a2/46/d1a246a5bde9dee179c3f13f6d9474b6.jpg" />
      )
    },
    {
      key: uuidv4(),
      content: (
        <Card imagen="https://i.pinimg.com/originals/94/f9/55/94f955d545010b4ccfdd9d63e13cc5a0.jpg" />
      )
    },
    {
      key: uuidv4(),
      content: (
        <Card imagen="https://cdnb.artstation.com/p/users/covers/000/099/217/default/3807537c7501122ce2e7cae56a3ea263.jpg?1452533519" />
      )
    },
    {
      key: uuidv4(),
      content: (
        <Card imagen="https://wallpaperaccess.com/full/2832177.jpg"  />
      ),
     
    }
  ];
  return (
    <div>
      <Carousel
        cards={cards}
        height="700px"
        width="60%"
        margin="0 auto"
        offset={2}
      />
    </div>
  );
}

export default World


