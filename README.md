Library Booking System Backend using Java <!-- omit in toc -->
========================================

TOC <!-- omit in toc -->
-------------------
- [Business Requirements](#business-requirements)
- [Architecture](#architecture)
  - [Overview](#overview)
- [Database](#database)
  - [ERD](#erd)


Business Requirements
-------------------

We want to create a booking system for a library, where customers can borrow books for a limited time.
- All articles in the library are categorized as NEW, CLASSIC, STANDARD
- As a customer you can borrow a maximum 5 articles at the same time (of borrowing)
- All articles must be returned no later than 30 days after the time of borrowing
- One customer can have a maximum of 7 articles borrowed at the same time, but a maximum of 2 NEW-articles


Architecture
-------------------

### Overview
![Overview](https://raw.githubusercontent.com/takeshi-omae-lws/media/master/LibraryBookingSystem.png)


Database
-------------------

### ERD
![ERD](https://raw.githubusercontent.com/takeshi-omae-lws/media/master/LibraryBookingSystemERD.png)