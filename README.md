# Membership Program – FirstClub

A Spring Boot backend system for a subscription-based **Membership Program** with tiered benefits, designed with a focus on extensibility, modularity, and clean abstractions.

---

## 📌 Problem Statement

Design a backend system for FirstClub that allows users to subscribe to membership plans with tiered benefits.

---

## ✅ Features Implemented

- Subscribe to a membership plan + tier combination (Monthly / Quarterly / Yearly × Silver / Gold / Platinum)
- Cancel an active subscription at any time
- Change membership plan (same tier, different billing cycle)
- Upgrade / downgrade membership tier
- Track current membership and expiry
- Pricing Catalogue — admin-configurable plan+tier combination prices
- Subscription history maintained per user

---

## 🏗️ Design Decisions & Abstractions

### Class Diagram
<img width="1317" height="654" alt="Screenshot 2026-06-15 at 10 10 05 AM" src="https://github.com/user-attachments/assets/91af2768-4da2-47f2-a046-353b72317d18" />


| Entity | Purpose |
|---|---|
| `User` | Holds current active subscription + full subscription history |
| `Subscription` | Represents a user's active contract with startDate, endDate, status |
| `PricingCatalogue` | Maps every Plan+Tier combination to a configurable final price |
| `Order` | Maps every Order to user |

### Key Design Patterns Used

**1. State Pattern** — `ISubscriptionState` with `ActiveSubscriptionState`, `ExpiredSubscriptionState`, `CancelledSubscriptionState`
- Each state knows its valid transitions (e.g. you cannot cancel an already-expired subscription)
- `SubscriptionStateFactory` resolves the correct state at runtime — no if-else chains in services

**2. Factory Pattern** — `SubscriptionStateFactory` decouples state resolution from business logic

**3. Interface Segregation** — Separate interfaces for each concern:
- `IUserService` → subscribe, cancel, track
- `IPlanSubscriptionService` → change plan
- `ITierSubscriptionService` → upgrade/downgrade tier
- `IPriceCatalogueService` → manage pricing catalogue

---

## 🗂️ Package Structure

```
com.example.membershipProgram
├── controller        → REST API layer
├── factory           → SubscriptionStateFactory
├── model
│   ├── dto           → Request DTOs
│   └── enums         → PlanType, TierType, SubscriptionStatus
├── repository        → JPA repositories
├── service
│   └── impl          → Service implementations
└── state             → State pattern classes
```

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok

---

## 📡 API Overview

### User APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/user` | Create a new user |
| GET | `/api/v1/user/{id}` | Get user by ID |
| POST | `/api/v1/user/subscribe/{userId}` | Subscribe to a plan + tier |
| DELETE | `/api/v1/user/cancelSubcription/{userId}` | Cancel active subscription |
| PUT | `/api/v1/user/changePlan/{userId}?planType=YEARLY` | Change billing plan |
| PUT | `/api/v1/user/changeTier/{userId}?tierType=GOLD` | Upgrade/downgrade tier |

### Pricing Catalogue APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/catalogue` | Add a plan+tier price entry |
| GET | `/api/v1/catalogue/{id}` | Get catalogue entry |
| PUT | `/api/v1/catalogue/update/{id}` | Update pricing |
| DELETE | `/api/v1/catalogue/delete/{id}` | Remove a catalogue entry |

### Order APIs
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/order` | Add an order |
| GET | `/api/v1/order/{id}` | Get an order entry |

---

## 💡 Assumptions

1. A user can select any plan + tier combination freely
2. Tier upgrade is allowed only when the user is not already at the highest tier (PLATINUM)
3. Subscription history is preserved — cancellation never deletes records
4. Price at time of subscription is stored on the Subscription for historical accuracy
5. There is no automatic downgrade rule for tiers
