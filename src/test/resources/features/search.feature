Feature: Search on booking.com

  Scenario Template: Search by different cities criteria
    Given User is looking for hotels in '<City>'
    When User does search
    And  Hotel '<Hotel>' should be on the first page
    Then Hotel '<Hotel>' rating is '<expectedRate>'

    Examples:
      | City     | Hotel                               | expectedRate |
      | Minsk    | Hampton by Hilton Minsk City Centre | 8,9          |
      | Bobruisk | Отель Турист                        | 7,9          |
      | Leysin   | Alpine Classic Hotel                | 8,1          |

