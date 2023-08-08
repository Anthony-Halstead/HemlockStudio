/**
 * @module World
 */

/** 
 * UUID generator module, used to generate unique keys for React components.
 * @external uuid
 * @see {@link https://www.npmjs.com/package/uuid|UUID}
 */
import { v4 as uuidv4 } from "uuid";

/**
 * Reusable Card component.
 * @see {@link module:Card|Card}
 */
import Card from "../reusables/Card";

/**
 * Reusable Carousel component.
 * @see {@link module:Carousel|Carousel}
 */
import Carousel from "../reusables/Carousel";

/**
 * Image representing tropical ruins.
 * @constant {string}
 */
import TropicalRuins from "../../images/TropicalRuins.png";

/**
 * Image representing dwarf statues.
 * @constant {string}
 */
import DwarfStatues from "../../images/DwarfStatues.png";

/**
 * The World component displays a carousel of cards with various world-related images.
 * 
 * It uses the Card and Carousel components to display these images. The images include
 * static imports from the local project and external URLs.
 * 
 * @function
 * @returns {JSX.Element} A carousel containing cards with world-related images.
 */
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


