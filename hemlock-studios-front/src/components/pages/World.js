import { v4 as uuidv4 } from "uuid";
import Card from "../reusables/Card";
import Carousel from "../reusables/Carousel";
import TropicalRuins from "../../images/TropicalRuins.png"
import DwarfStatues from "../../images/DwarfStatues.png"

function World() {
  let cards = [
    {
      key: uuidv4(),
      content: (
        <Card imagen={TropicalRuins}/>
      )
    },
    {
      key: uuidv4(),
      content: (
        <Card imagen={DwarfStatues}/>
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


