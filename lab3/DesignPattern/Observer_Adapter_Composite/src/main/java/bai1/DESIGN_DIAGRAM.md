# Observer Design Pattern - UML Diagrams

## Scenario 1: Stock Price Notification System

```
┌─────────────────────────────────────────────────────────────────┐
│                    <<interface>>                                 │
│                    Observer                                      │
├─────────────────────────────────────────────────────────────────┤
│ + update(stock: Stock, oldPrice: double, newPrice: double): void│
└─────────────────────────────────────────────────────────────────┘
                          △
                          │
                          │ implements
                          │
         ┌────────────────┴────────────────┐
         │                                  │
┌────────┴──────────┐            ┌─────────┴─────────┐
│   Investor        │            │  InvestmentFund   │
├───────────────────┤            ├───────────────────┤
│ - name: String    │            │ - fundName: String│
├───────────────────┤            ├───────────────────┤
│ + update()        │            │ + update()        │
└───────────────────┘            └───────────────────┘


┌─────────────────────────────────────────────────────────────────┐
│                         Stock                                    │
├─────────────────────────────────────────────────────────────────┤
│ - symbol: String                                                 │
│ - price: double                                                  │
│ - observers: List<Observer>                                      │
├─────────────────────────────────────────────────────────────────┤
│ + attach(observer: Observer): void                               │
│ + detach(observer: Observer): void                               │
│ + notifyObservers(oldPrice: double, newPrice: double): void     │
│ + setPrice(price: double): void                                  │
│ + getPrice(): double                                             │
│ + getSymbol(): String                                            │
└─────────────────────────────────────────────────────────────────┘
         │
         │ notifies
         │
         ▼
    ┌────────┐
    │Observer│ *
    └────────┘
```

## Scenario 2: Task Management Notification System

```
┌─────────────────────────────────────────────────────────────────┐
│                    <<interface>>                                 │
│                    TaskObserver                                  │
├─────────────────────────────────────────────────────────────────┤
│ + onTaskUpdate(task: Task, oldStatus: String,                   │
│                newStatus: String): void                          │
└─────────────────────────────────────────────────────────────────┘
                          △
                          │
                          │ implements
                          │
         ┌────────────────┴────────────────┐
         │                                  │
┌────────┴──────────┐            ┌─────────┴──────────┐
│   TeamMember      │            │  ProjectManager    │
├───────────────────┤            ├────────────────────┤
│ - name: String    │            │ - name: String     │
│ - role: String    │            ├────────────────────┤
├───────────────────┤            │ + onTaskUpdate()   │
│ + onTaskUpdate()  │            └────────────────────┘
└───────────────────┘


┌─────────────────────────────────────────────────────────────────┐
│                         Task                                     │
├─────────────────────────────────────────────────────────────────┤
│ - taskId: String                                                 │
│ - title: String                                                  │
│ - status: String                                                 │
│ - observers: List<TaskObserver>                                  │
├─────────────────────────────────────────────────────────────────┤
│ + addObserver(observer: TaskObserver): void                      │
│ + removeObserver(observer: TaskObserver): void                   │
│ + notifyObservers(oldStatus: String, newStatus: String): void   │
│ + setStatus(status: String): void                                │
│ + getStatus(): String                                            │
│ + getTaskId(): String                                            │
│ + getTitle(): String                                             │
└─────────────────────────────────────────────────────────────────┘
         │
         │ notifies
         │
         ▼
    ┌────────────┐
    │TaskObserver│ *
    └────────────┘
```

## Pattern Structure

**Observer Pattern Components:**

1. **Subject** (Stock, Task): Maintains list of observers and notifies them of changes
2. **Observer Interface** (Observer, TaskObserver): Defines update method for observers
3. **Concrete Observers** (Investor, InvestmentFund, TeamMember, ProjectManager): Implement update logic

**Key Benefits:**

- Loose coupling between subjects and observers
- Dynamic subscription/unsubscription
- Broadcast communication
- Open/Closed Principle compliance
