Feature: RetrieveData
    Scenario: Retrieve values by ID
    Given the user is in the demo.aspnetawesome website
    And there are existing records in the Grid filter row server data table
    When the user provides the ID <InputID> for a specific record
    Then the system associated with <InputID> should return all values <Person>, <Food>, <Price>, <Date>, <Country>, <Meals>, <Chef>
    
    Examples:
      | InputID | Person     | Food             | Price | Date        | Country         | Meals             | Chef               |
      | "1991"  | "Mary"     | "Cheesecake"     | "45"  | "10/7/2010" | "Hatton Garden" | "Mango"           | "Charles Duchemin" |
      | "2021"  | "Courtney" | "Shepherd's pie" | "45"  | "5/23/2018" | "Farringdon"    | "Artichoke"       | "Bruce Nolan"      |
      | "1923"  | "Russell"  | "Big Salad"      | "50"  | "8/19/2012" | "Jisina"        | "Wheat"           | "Casse Croute"     |
      | "1891"  | "Sergio"   | "Apple Pie"      | "21"  | "3/23/2010" | "Elwynn Forest" | "Walnuts, Papaya" | "Casse Croute"     |

